package com.example.domain.usecase

import com.example.domain.entity.AddToCartRequestEntity
import com.example.domain.entity.AddToCartResponseEntity
import com.example.domain.repository.AppRepository
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    val repository: AppRepository
) {
    suspend fun invoke(request: AddToCartRequestEntity, token: String): AddToCartResponseEntity {
        return repository.addToCart(request, token)
    }
}


