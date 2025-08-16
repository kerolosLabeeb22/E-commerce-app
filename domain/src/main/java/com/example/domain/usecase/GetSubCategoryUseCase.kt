package com.example.domain.usecase

import com.example.domain.entity.SubCategoryDataItemEntity
import com.example.domain.repository.AppRepository
import javax.inject.Inject

class GetSubCategoryUseCase @Inject constructor(
    val repository: AppRepository
) {

    suspend fun invoke(categoryId: String): List<SubCategoryDataItemEntity> {
        return repository.getSubCategory(categoryId)
    }
}