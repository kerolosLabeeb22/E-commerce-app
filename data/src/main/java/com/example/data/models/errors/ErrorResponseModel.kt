package com.example.data.models.errors

import com.google.gson.annotations.SerializedName

data class ErrorResponseModel(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("errors")
	val errors: ErrorsModel? = null
)

data class ErrorsModel(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("param")
	val param: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("value")
	val value: String? = null
)
