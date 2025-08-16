package com.example.domain.repository

import com.example.domain.Utils.ApiResult
import com.example.domain.entity.AuthResponseEntity
import com.example.domain.entity.CategoryDataItemEntity
import com.example.domain.entity.ProductDataItemEntity

interface AppRepository {

    suspend fun login(email: String, password: String): ApiResult<AuthResponseEntity>

    suspend fun register(
        email: String,
        password: String,
        phoneNumber: String,
        fullName: String
    ): ApiResult<AuthResponseEntity>

    suspend fun getCategories(): List<CategoryDataItemEntity>

    suspend fun getProducts(): List<ProductDataItemEntity>
}

interface AppOnlineDataSource {
    suspend fun login(email: String, password: String): ApiResult<AuthResponseEntity>

    suspend fun register(
        email: String,
        password: String,
        phoneNumber: String,
        fullName: String
    ): ApiResult<AuthResponseEntity>

    suspend fun getCategories(): List<CategoryDataItemEntity>

    suspend fun getProducts(): List<ProductDataItemEntity>

}