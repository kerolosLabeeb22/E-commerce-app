package com.example.domain.usecase

import com.example.domain.entity.WishDataItemEntity
import com.example.domain.repository.AppRepository
import javax.inject.Inject

class GetWishlistUseCase @Inject constructor(
    val repository: AppRepository
) {
    suspend fun invoke(token: String): List<WishDataItemEntity> {
        return repository.getWishlist(token)
    }
}