package com.example.domain.usecase

import com.example.domain.Utils.ApiResult
import com.example.domain.entity.AuthResponseEntity
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend fun invoke(emil: String, password: String): ApiResult<AuthResponseEntity> {
        return repository.login(emil, password)
    }
}