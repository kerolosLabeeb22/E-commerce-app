package com.example.domain.usecase

import com.example.domain.entity.RemoveFromWishlistResponseEntity
import com.example.domain.repository.AppRepository
import javax.inject.Inject

class RemoveFromWishlistUseCase @Inject constructor(
    val repository: AppRepository
) {
    suspend fun invoke(productId: String, token: String): RemoveFromWishlistResponseEntity {
        return repository.removeFromWishlist(productId, token)
    }
}


