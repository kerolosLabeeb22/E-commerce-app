package com.example.data.models.wishlist

import com.google.gson.annotations.SerializedName

data class RemoveFromWishlistResponse(

	@field:SerializedName("data")
	val data: List<String?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
