package com.example.domain.repository

import com.example.domain.Utils.ApiResult
import com.example.domain.entity.AuthResponseEntity

interface AuthRepository {

    suspend fun login(email: String, password: String): ApiResult<AuthResponseEntity>

    suspend fun register(
        email: String,
        password: String,
        phoneNumber: String,
        fullName: String
    ): ApiResult <AuthResponseEntity>
}

interface AuthOnlineDataSource {
    suspend fun login(email: String, password: String): ApiResult<AuthResponseEntity>

    suspend fun register(
        email: String,
        password: String,
        phoneNumber: String,
        fullName: String
    ): ApiResult<AuthResponseEntity>

}