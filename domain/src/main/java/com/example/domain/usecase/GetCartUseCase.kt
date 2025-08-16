package com.example.domain.usecase

import com.example.domain.entity.CartDataEntity
import com.example.domain.repository.AppRepository
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    val repository: AppRepository
) {

    suspend fun invoke(token: String): CartDataEntity {
        return repository.getCart(token)
    }
}