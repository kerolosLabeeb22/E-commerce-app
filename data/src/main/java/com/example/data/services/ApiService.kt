package com.example.data.services

import com.example.data.models.auth.AuthResponseModel
import com.example.data.models.cateogry.CategoryResponse
import com.example.data.models.product.ProductsResponse
import retrofit2.Response

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
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
}