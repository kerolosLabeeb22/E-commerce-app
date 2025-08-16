package com.example.home_screen.cart

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Constant
import com.example.domain.entity.CartProductsItemEntity
import com.example.domain.entity.CartResponseEntity
import com.example.domain.usecase.GetCartUseCase
import com.example.domain.usecase.RemoveFromCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    val getCartUseCase: GetCartUseCase,
    val removeFromCartUseCase: RemoveFromCartUseCase

) : ViewModel() {

    val products = mutableStateListOf<CartProductsItemEntity>()
    val errorState = mutableStateOf("")

    fun getCartProducts(

    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
//
                val response = getCartUseCase.invoke(Constant.TOKEN)

                val safeProducts = response.products
                    ?.filterNotNull()?.mapNotNull { it }
                    ?: emptyList()

                // update the main thread
                withContext(Dispatchers.Main) {
                    products.clear()
                    products.addAll(safeProducts)
                }


            } catch (e: Exception) {
                errorState.value = e.message ?: ""
            }
        }


    }

    fun removeFromCart(
        productId: String,
        onSuccess: (CartResponseEntity) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val response = removeFromCartUseCase.invoke(productId, Constant.TOKEN)

                withContext(Dispatchers.Main) {
                    if (response.status == "success") {
                        onSuccess(response)
                    } else {
                        onFailure("unsuccessful")
                    }
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onFailure(e.localizedMessage ?: "Unexpected error")
                }
            }
        }

    }
}