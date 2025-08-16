package com.example.e_commerceapp.homescreen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.CategoryDataItemEntity
import com.example.domain.entity.ProductDataItemEntity
import com.example.domain.usecase.GetCategoryUseCase
import com.example.domain.usecase.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getCategoryUseCase: GetCategoryUseCase,
    val getProductUseCase: GetProductUseCase
) : ViewModel() {

    val categoryList = mutableStateListOf<CategoryDataItemEntity>()
    val productList = mutableStateListOf<ProductDataItemEntity>()
    val electronicProductList = mutableStateListOf<ProductDataItemEntity>()

    val newArrivalProductList = mutableStateListOf<ProductDataItemEntity>()
    val errorState = mutableStateOf("")

    fun getCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getCategoryUseCase.invoke()
                categoryList.addAll(response)
            } catch (e: Exception) {
                //e.printStackTrace()
                errorState.value = e.message ?: ""
            }
        }
    }

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getProductUseCase.invoke()
                if (response.isNotEmpty()) {
                    productList.clear()
                    productList.addAll(response)
                }
            } catch (e: Exception) {
                //e.printStackTrace()
                errorState.value = e.message ?: ""
            }
        }
    }

    fun getElectronicProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getProductUseCase.invoke()

                val filter = response.filter {
                    it.category?.id == "6439d2d167d9aa4ca970649f"
                }

                if (filter.isNotEmpty()) {
                    electronicProductList.clear()
                    electronicProductList.addAll(filter)
                }
            } catch (e: Exception) {
                //e.printStackTrace()
                errorState.value = e.message ?: ""

            }
        }
    }

    fun getNewArrivalProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getProductUseCase.invoke()

                val sort = response.sortedByDescending {
                    it.createdAt
                }

                if (sort.isNotEmpty()) {
                    newArrivalProductList.clear()
                    newArrivalProductList.addAll(sort)
                }
            } catch (e: Exception) {
                //e.printStackTrace()
                errorState.value = e.message ?: ""

            }
        }
    }
}