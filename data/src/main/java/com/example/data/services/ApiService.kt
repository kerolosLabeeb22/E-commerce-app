package com.example.data.services

import com.example.data.Constant
import com.example.data.api.model.AddToCartResponse
import com.example.data.models.auth.AuthResponseModel
import com.example.data.models.cateogry.CategoryResponse
import com.example.data.models.cateogry.SubCategoryResponse
import com.example.data.models.product.ProductsResponse
import com.example.data.models.wishlist.AddToWishlistRequest
import com.example.data.models.wishlist.AddToWishlistResponse
import com.example.data.models.wishlist.RemoveFromWishlistResponse
import com.example.data.models.wishlist.WishlistResponse
import com.example.domain.entity.AddToCartRequestEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @FormUrlEncoded
    @POST("auth/signin")
    suspend fun login(
        @Field("email") emailAddress: String,
        @Field("password") password: String
    ): retrofit2.Response<AuthResponseModel>


    @FormUrlEncoded
    @POST("auth/signup")
    suspend fun register(
        @Field("email") emailAddress: String,
        @Field("password") password: String,
        @Field("name") fullName: String,
        @Field("phone") phoneNumber: String,
        @Field("rePassword") passwordConfirmation: String
    ): retrofit2.Response<AuthResponseModel>

    @GET("categories")
    suspend fun getCategory(
        @Query("category") categorySlug: String? = null,  // e.g. "home-appliance"
        @Query("sort") sort: String? = null,              // e.g. "-createdAt" for new arrivals
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null
    ): Response<CategoryResponse>

    @GET("products")
    suspend fun getProduct(): Response<ProductsResponse>

    @POST("wishlist")
    suspend fun addToWishlist(
        @Body addToWishlistRequest: AddToWishlistRequest,
        @Header("token") token: String = Constant.TOKEN
    ): Response<AddToWishlistResponse>

    @DELETE("wishlist/{productId}")
    suspend fun removeFromWishlist(
        @Path("productId") productId: String,
        @Header("token") token: String
    ): Response<RemoveFromWishlistResponse>

    @POST("cart")
    suspend fun addToCart(
        @Body request: AddToCartRequestEntity,
        @Header("token") token: String
    ): Response<AddToCartResponse>

    @GET("subcategories")
    suspend fun getSubCategory(
        @Query("category") categoryId: String
    ): Response<SubCategoryResponse>

    @GET("wishlist")
    suspend fun getWishlist(
        @Header("token") token: String = Constant.TOKEN
    ): Response<WishlistResponse>


}