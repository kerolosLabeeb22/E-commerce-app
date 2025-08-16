package com.example.domain.usecase

import com.example.domain.entity.ProductDataItemEntity
import com.example.domain.repository.AppRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    val repository: AppRepository
) {
    suspend fun invoke(): List<ProductDataItemEntity> {
        return repository.getProducts()
    }
}