package com.example.data.models.cateogry

import com.google.gson.annotations.SerializedName

data class CategoryResponse(

	@field:SerializedName("metadata")
	val metadata: CategoryMetadata? = null,

	@field:SerializedName("data")
	val data: List<CategoryDataItem?>? = null,

	@field:SerializedName("results")
	val results: Int? = null
)

data class CategoryDataItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class CategoryMetadata(

	@field:SerializedName("numberOfPages")
	val numberOfPages: Int? = null,

	@field:SerializedName("limit")
	val limit: Int? = null,

	@field:SerializedName("currentPage")
	val currentPage: Int? = null
)
