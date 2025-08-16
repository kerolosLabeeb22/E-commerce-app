package com.example.domain.usecase

import com.example.domain.entity.AddToWishlistResponseEntity
import com.example.domain.repository.AppRepository
import javax.inject.Inject

class AddToWishlistUseCase @Inject constructor(
    val repository: AppRepository
) {

    suspend fun invoke(
        productId: String,
        token: String
    ): AddToWishlistResponseEntity {
        return repository.addToWishlist(productId, token)
    }
}