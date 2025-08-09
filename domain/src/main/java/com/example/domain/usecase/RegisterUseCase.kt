package com.example.domain.usecase

import com.example.domain.Utils.ApiResult
import com.example.domain.entity.AuthResponseEntity
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend fun invoke(
        emil: String,
        password: String,
        phoneNumber: String,
        fullName: String
    ): ApiResult<AuthResponseEntity> {
        return repository.register(emil, password, phoneNumber, fullName)
    }
}