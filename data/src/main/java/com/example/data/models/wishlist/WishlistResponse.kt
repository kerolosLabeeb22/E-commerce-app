package com.example.data.models.wishlist

import com.example.data.models.product.ProductBrand
import com.example.data.models.product.ProductCategory
import com.google.gson.annotations.SerializedName

data class WishlistResponse(

	@field:SerializedName("data")
	val data: List<WishDataItem?>? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class WishSubcategoryItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class WishlistCategory(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class WishlistBrand(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class WishDataItem(

	@field:SerializedName("sold")
	val sold: Int? = null,

	@field:SerializedName("images")
	val images: List<String?>? = null,

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("imageCover")
	val imageCover: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("ratingsQuantity")
	val ratingsQuantity: Int? = null,

	@field:SerializedName("ratingsAverage")
	val ratingsAverage: Any? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("_id")
	val _wishid: String? = null,

	@field:SerializedName("id")
	val wishId: String? = null,

	@field:SerializedName("subcategory")
	val wishlistSubcategory: List<WishSubcategoryItem?>? = null,

	@field:SerializedName("category")
	val wishCategory: ProductCategory? = null,

	@field:SerializedName("brand")
	val wishlistBrand: ProductBrand? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("availableColors")
	val availableColors: List<Any?>? = null
)
