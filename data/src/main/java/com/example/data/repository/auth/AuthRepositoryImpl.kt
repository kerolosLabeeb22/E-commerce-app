package com.example.data.repository.auth

import com.example.domain.Utils.ApiResult
import com.example.domain.entity.AuthResponseEntity
import com.example.domain.repository.AuthOnlineDataSource
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val onlineDataSource: AuthOnlineDataSource
) : AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): ApiResult<AuthResponseEntity> {
        return onlineDataSource.login(email,password)
    }

    override suspend fun register(
        email: String,
        password: String,
        phoneNumber: String,
        fullName: String
    ): ApiResult<AuthResponseEntity> {
        return onlineDataSource.register(email,password,phoneNumber,fullName)
    }
}