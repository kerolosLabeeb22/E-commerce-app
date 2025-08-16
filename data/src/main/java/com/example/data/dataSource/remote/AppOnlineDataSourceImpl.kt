package com.example.data.dataSource.remote

import com.example.data.dataSource.utils.mapResource
import com.example.data.dataSource.utils.safeApiCalls
import com.example.data.mappers.toEntity
import com.example.data.mappers.tonEntity
import com.example.data.models.wishlist.AddToWishlistRequest
import com.example.data.services.ApiService
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
import javax.inject.Inject

class AppOnlineDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : AppOnlineDataSource {
    override suspend fun login(
        email: String, password: String
    ): ApiResult<AuthResponseEntity> {
        return safeApiCalls {
            apiService.login(email, password)
        }.mapResource {
            it.tonEntity()
        }
    }

    override suspend fun register(
        email: String, password: String, phoneNumber: String, fullName: String
    ): ApiResult<AuthResponseEntity> {
        return safeApiCalls {
            apiService.register(email, password, phoneNumber, fullName, password)
        }.mapResource {
            it.tonEntity()
        }
    }

    override suspend fun getCategories(): List<CategoryDataItemEntity> {
        return apiService.getCategory().body()?.data?.map {
            it!!.toEntity()
        } ?: emptyList()
    }

    override suspend fun getProducts(): List<ProductDataItemEntity> {
        return apiService.getProduct().body()?.data?.map {
            it!!.toEntity()
        } ?: emptyList()
    }

    override suspend fun addToWishlist(
        productId: String,
        token: String
    ): AddToWishlistResponseEntity {
        return apiService.addToWishlist(AddToWishlistRequest(productId), token).body()!!.toEntity()
    }

    override suspend fun removeFromWishlist(
        productId: String,
        token: String
    ): RemoveFromWishlistResponseEntity {
        return apiService.removeFromWishlist(productId, token).body()!!.toEntity()
    }

    override suspend fun addToCart(
        request: AddToCartRequestEntity,
        token: String
    ): AddToCartResponseEntity {
        return apiService.addToCart(request, token).body()!!.toEntity()
    }

    override suspend fun getSubCategory(categoryId: String): List<SubCategoryDataItemEntity> {
        return apiService.getSubCategory(categoryId).body()?.data?.map {
            it!!.toEntity()
        } ?: emptyList()
    }
}