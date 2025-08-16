package com.example.data.repository

import com.example.domain.Utils.ApiResult
import com.example.domain.entity.AddToCartRequestEntity
import com.example.domain.entity.AddToCartResponseEntity
import com.example.domain.entity.AddToWishlistResponseEntity
import com.example.domain.entity.AuthResponseEntity
import com.example.domain.entity.CategoryDataItemEntity
import com.example.domain.entity.ProductDataItemEntity
import com.example.domain.entity.RemoveFromWishlistResponseEntity
import com.example.domain.entity.SubCategoryDataItemEntity
import com.example.domain.repository.AppOnlineDataSource
import com.example.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(

    private val onlineDataSource: AppOnlineDataSource
) : AppRepository {
    override suspend fun login(
        email: String,
        password: String
    ): ApiResult<AuthResponseEntity> {
        return onlineDataSource.login(email, password)
    }

    override suspend fun register(
        email: String,
        password: String,
        phoneNumber: String,
        fullName: String
    ): ApiResult<AuthResponseEntity> {
        return onlineDataSource.register(email, password, phoneNumber, fullName)
    }

    override suspend fun getCategories(): List<CategoryDataItemEntity> {
        return onlineDataSource.getCategories()
    }

    override suspend fun getProducts(): List<ProductDataItemEntity> {
        return onlineDataSource.getProducts()
    }

    override suspend fun addToWishlist(
        productId: String,
        token: String
    ): AddToWishlistResponseEntity {
        return onlineDataSource.addToWishlist(productId, token)
    }

    override suspend fun removeFromWishlist(
        productId: String,
        token: String
    ): RemoveFromWishlistResponseEntity {
        return onlineDataSource.removeFromWishlist(productId, token)
    }

    override suspend fun addToCart(
        request: AddToCartRequestEntity,
        token: String
    ): AddToCartResponseEntity {
        return onlineDataSource.addToCart(request, token)
    }

    override suspend fun getSubCategory(categoryId: String): List<SubCategoryDataItemEntity> {
        return onlineDataSource.getSubCategory(categoryId)
    }
}