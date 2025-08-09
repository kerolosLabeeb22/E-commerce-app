package com.example.data.models.auth

import com.google.gson.annotations.SerializedName

data class AuthResponseModel(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("user")
	val userModel: UserModel? = null,

	@field:SerializedName("token")
	val token: String? = null
)

data class UserModel(

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
