package com.example.e_commerceapp.favorite

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Constant
import com.example.data.models.wishlist.AddToWishlistRequest
import com.example.domain.entity.RemoveFromWishlistResponseEntity
import com.example.domain.entity.WishDataItemEntity
import com.example.domain.usecase.AddToWishlistUseCase
import com.example.domain.usecase.GetWishlistUseCase
import com.example.domain.usecase.RemoveFromWishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    val getWishlistUseCase: GetWishlistUseCase,
    val removeFromWishlistUseCase: RemoveFromWishlistUseCase,
    val addToWishlistUseCase: AddToWishlistUseCase
) : ViewModel() {
    val wishlist = mutableStateListOf<WishDataItemEntity>()
    val errorState = mutableStateOf("")


    fun getWishlist(

    ) {

        viewModelScope.launch(Dispatchers.IO) {
            try {

                val response = getWishlistUseCase.invoke(Constant.TOKEN)
                wishlist.clear()
                wishlist.addAll(response)

            } catch (e: Exception) {
                errorState.value = e.message ?: ""
            }
        }

    }

    fun deleteFromWishlist(
        productId: String,
        onSuccess: (RemoveFromWishlistResponseEntity) -> Unit,
        onFailure: (message: String) -> Unit
    ) {

        viewModelScope.launch(Dispatchers.IO) {
            try {

                val response = removeFromWishlistUseCase.invoke(productId, Constant.TOKEN)

                withContext(Dispatchers.Main) {
                    if (response.status == "success") {
                        onSuccess(response)
                    } else {
                        onFailure("unsuccessful")
                    }
                }


            } catch (e: Exception) {
                onFailure(e.message ?: "Unknown error")
            }
        }

    }

    fun addToWishlist(
        productId: String,
        token: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {


                val request = AddToWishlistRequest(productId)

                val response = addToWishlistUseCase.invoke(productId, token)
                if (response.status == "success") {
                    Log.d("Wishlist", "Added: ${response.data?.size} items in wishlist")
                } else {
                    errorState.value = response.message ?: ""
                }


            } catch (e: Exception) {
                errorState.value = e.message ?: ""

            }
        }




    }
}