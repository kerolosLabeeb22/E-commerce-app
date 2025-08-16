package com.example.data.models.cart

import com.google.gson.annotations.SerializedName

data class CartResponse(

	@field:SerializedName("data")
	val cartData: CartData? = null,

	@field:SerializedName("numOfCartItems")
	val numOfCartItems: Int? = null,

	@field:SerializedName("cartId")
	val cartId: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class CartSubcategoryItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class CartBrand(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class CartData(

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
	val products: List<CartProductsItem?>? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class CartCategory(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class Product(

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("imageCover")
	val imageCover: String? = null,

	@field:SerializedName("_id")
	val _ProductId: String? = null,

	@field:SerializedName("id")
	val CartId: String? = null,

	@field:SerializedName("subcategory")
	val subcategory: List<CartSubcategoryItem?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("category")
	val cartCategory: CartCategory? = null,

	@field:SerializedName("brand")
	val cartBrand: CartBrand? = null,

	@field:SerializedName("ratingsAverage")
	val ratingsAverage: Any? = null
)

data class CartProductsItem(

	@field:SerializedName("product")
	val product: Product? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null
)
