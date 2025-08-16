package com.example.domain.usecase

import com.example.domain.entity.CategoryDataItemEntity
import com.example.domain.repository.AppRepository
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    val repository: AppRepository
) {
    suspend fun invoke(): List<CategoryDataItemEntity> {
        return repository.getCategories()
    }
}