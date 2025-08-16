package com.example.data.models.cateogry

import com.google.gson.annotations.SerializedName

data class SubCategoryResponse(

	@field:SerializedName("metadata")
	val subCategoryMetadata: SubCategoryMetadata? = null,

	@field:SerializedName("data")
	val data: List<SubCategoryDataItem?>? = null,

	@field:SerializedName("results")
	val results: Int? = null
)

data class SubCategoryMetadata(

	@field:SerializedName("numberOfPages")
	val numberOfPages: Int? = null,

	@field:SerializedName("nextPage")
	val nextPage: Int? = null,

	@field:SerializedName("limit")
	val limit: Int? = null,

	@field:SerializedName("currentPage")
	val currentPage: Int? = null
)

data class SubCategoryDataItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
