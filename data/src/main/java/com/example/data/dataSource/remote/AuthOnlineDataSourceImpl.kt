package com.example.data.dataSource.remote

import com.example.data.dataSource.utils.mapResource
import com.example.data.dataSource.utils.safeApiCalls
import com.example.data.mappers.tonEntity
import com.example.data.models.errors.ErrorResponseModel
import com.example.data.services.AuthService
import com.example.domain.Utils.ApiResult
import com.example.domain.entity.AuthResponseEntity
import com.example.domain.errors.NoDataError
import com.example.domain.errors.ServerError
import com.example.domain.repository.AuthOnlineDataSource
import com.google.gson.Gson
import java.io.IOException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class AuthOnlineDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthOnlineDataSource {
    override suspend fun login(
        email: String, password: String
    ): ApiResult<AuthResponseEntity> {
        return safeApiCalls {
            authService.login(email, password)
        }.mapResource {
            it.tonEntity()
        }
    }

    override suspend fun register(
        email: String, password: String, phoneNumber: String, fullName: String
    ): ApiResult<AuthResponseEntity> {
        return safeApiCalls {
            authService.register(email, password, phoneNumber, fullName, password)
        }.mapResource {
            it.tonEntity()
        }
    }
}