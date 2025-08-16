package com.example.domain.usecase

import com.example.domain.entity.CartResponseEntity
import com.example.domain.repository.AppRepository
import javax.inject.Inject

class RemoveFromCartUseCase @Inject constructor(
    val repository: AppRepository

) {
    suspend fun invoke(productId: String, token: String): CartResponseEntity {
        return repository.removeFromCart(productId, token)
    }
}