package com.example.domain.entity


data class SubCategoryResponseEntity(

    val subCategoryMetadata: SubCategoryMetadataEntity? = null,

    val data: List<SubCategoryDataItemEntity?>? = null,

    val results: Int? = null
)

data class SubCategoryMetadataEntity(

    val numberOfPages: Int? = null,

    val nextPage: Int? = null,

    val limit: Int? = null,

    val currentPage: Int? = null
)

data class SubCategoryDataItemEntity(

    val createdAt: String? = null,

    val name: String? = null,

    val id: String? = null,

    val category: String? = null,

    val slug: String? = null,

    val updatedAt: String? = null
)
