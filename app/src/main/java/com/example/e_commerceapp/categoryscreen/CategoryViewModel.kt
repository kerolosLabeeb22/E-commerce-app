package com.example.e_commerceapp.categoryscreen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.CategoryDataItemEntity
import com.example.domain.entity.SubCategoryDataItemEntity
import com.example.domain.usecase.GetCategoryUseCase
import com.example.domain.usecase.GetSubCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    val getCategoryUseCase: GetCategoryUseCase,
    val getSubCategoryUseCase: GetSubCategoryUseCase
) : ViewModel() {

    val categoryList = mutableStateListOf<CategoryDataItemEntity>()

    val subCategoryList = mutableStateListOf<SubCategoryDataItemEntity>()

    val errorState = mutableStateOf("")

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            try {


                val response = getCategoryUseCase.invoke()

                categoryList.addAll(response)


            } catch (e: Exception) {
                errorState.value = e.message ?: ""
            }
        }
    }

    fun getSubCategory(
        categoryId: String,

        ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val response = getSubCategoryUseCase.invoke(categoryId)
                subCategoryList.clear()
                if (response.isNotEmpty()) subCategoryList.addAll(response)

            } catch (e: Exception) {
                errorState.value = e.message ?: ""
            }
        }
    }
}