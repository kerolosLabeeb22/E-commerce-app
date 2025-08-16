package com.example.data.models.product

import com.example.data.models.cart.CartBrand
import com.example.data.models.cart.CartCategory
import com.example.data.models.cart.CartSubcategoryItem
import com.example.data.models.cateogry.CategoryMetadata
import com.google.gson.annotations.SerializedName

data class ProductsResponse(

	@field:SerializedName("metadata")
	val categoryMetadata: CategoryMetadata? = null,

	@field:SerializedName("data")
	val data: List<ProductDataItem?>? = null,

	@field:SerializedName("results")
	val results: Int? = null
)

data class SubcategoryItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class ProductDataItem(

	@field:SerializedName("sold")
	val sold: Int? = null,

	@field:SerializedName("images")
	val images: List<String?>? = null,

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("availableColors")
	val availableColors: List<Any?>? = null,

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

	@field:SerializedName("_id")
	val _id: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("subcategory")
	val subcategory: List<CartSubcategoryItem?>? = null,

	@field:SerializedName("category")
	val category: CartCategory? = null,

	@field:SerializedName("brand")
	val brand: CartBrand? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("priceAfterDiscount")
	val priceAfterDiscount: Int? = null
)

data class ProductBrand(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class ProductCategory(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class ProductMetadata(

	@field:SerializedName("numberOfPages")
	val numberOfPages: Int? = null,

	@field:SerializedName("nextPage")
	val nextPage: Int? = null,

	@field:SerializedName("limit")
	val limit: Int? = null,

	@field:SerializedName("currentPage")
	val currentPage: Int? = null
)
