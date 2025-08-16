package com.example.data.api.model

import com.google.gson.annotations.SerializedName

data class AddToCartResponse(

	@field:SerializedName("data")
	val data: AddToCartData? = null,

	@field:SerializedName("numOfCartItems")
	val numOfCartItems: Int? = null,

	@field:SerializedName("cartId")
	val cartId: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class AddToCartProductsItem(

	@field:SerializedName("product")
	val product: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null
)

data class AddToCartData(

	@field:SerializedName("cartOwner")
	val cartOwner: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("totalCartPrice")
	val totalCartPrice: Int? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("products")
	val products: List<AddToCartProductsItem?>? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
