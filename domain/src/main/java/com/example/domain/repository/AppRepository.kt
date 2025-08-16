package com.example.domain.repository

import com.example.domain.Utils.ApiResult
import com.example.domain.entity.AddToCartRequestEntity
import com.example.domain.entity.AddToCartResponseEntity
import com.example.domain.entity.AddToWishlistResponseEntity
import com.example.domain.entity.AuthResponseEntity
import com.example.domain.entity.CategoryDataItemEntity
import com.example.domain.entity.ProductDataItemEntity
import com.example.domain.entity.RemoveFromWishlistResponseEntity
import com.example.domain.entity.SubCategoryDataItemEntity
import com.example.domain.entity.WishDataItemEntity

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
    suspend fun addToWishlist(productId: String, token: String): AddToWishlistResponseEntity

    suspend fun removeFromWishlist(
        productId: String,
        token: String
    ): RemoveFromWishlistResponseEntity

    suspend fun addToCart(
        request: AddToCartRequestEntity,
        token: String
    ): AddToCartResponseEntity

    suspend fun getSubCategory(categoryId: String): List<SubCategoryDataItemEntity>

    suspend fun getWishlist(token: String): List<WishDataItemEntity>


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

    suspend fun addToWishlist(productId: String, token: String): AddToWishlistResponseEntity

    suspend fun removeFromWishlist(
        productId: String,
        token: String
    ): RemoveFromWishlistResponseEntity

    suspend fun addToCart(
        request: AddToCartRequestEntity,
        token: String
    ): AddToCartResponseEntity

    suspend fun getSubCategory(categoryId: String): List<SubCategoryDataItemEntity>

    suspend fun getWishlist(token: String): List<WishDataItemEntity>

}