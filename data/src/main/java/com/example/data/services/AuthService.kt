package com.example.data.services

import com.example.data.models.auth.AuthResponseModel

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import javax.inject.Named

interface AuthService {

    @FormUrlEncoded
    @POST("auth/signin")
    suspend fun login(
        @Field("email") emailAddress: String,
        @Field("password") password: String
    ): retrofit2.Response<AuthResponseModel>


    @FormUrlEncoded
    @POST("auth/signup")
    suspend fun register(
        @Field("email") emailAddress:String,
        @Field("password") password: String,
        @Field("name") fullName: String,
        @Field("phone" ) phoneNumber: String,
        @Field("rePassword") passwordConfirmation: String
    ) : retrofit2.Response<AuthResponseModel>
}