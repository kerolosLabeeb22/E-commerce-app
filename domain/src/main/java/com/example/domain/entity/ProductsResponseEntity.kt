package com.example.domain.entity


data class ProductsResponseEntity(

	val categoryMetadata: CategoryMetadataEntity? = null,

	val data: List<ProductDataItemEntity?>? = null,

	val results: Int? = null
)

data class SubcategoryItemEntity(

	val name: String? = null,

	val id: String? = null,

	val category: String? = null,

	val slug: String? = null
)

data class ProductDataItemEntity(

	val sold: Int? = null,

	val images: List<String?>? = null,

	val quantity: Int? = null,

	val availableColors: List<Any?>? = null,

	val imageCover: String? = null,
	val description: String? = null,

	val title: String? = null,

	val ratingsQuantity: Int? = null,

	val ratingsAverage: Any? = null,

	val createdAt: String? = null,

	val price: Int? = null,

	val _id: String? = null,

	val id: String? = null,

	val subcategory: List<CartSubcategoryItemEntity?>? = null,

	val category: CartCategoryEntity? = null,

	val brand: CartBrandEntity? = null,

	val slug: String? = null,

	val updatedAt: String? = null,

	val priceAfterDiscount: Int? = null
)

data class ProductBrandEntity(

	val image: String? = null,

	val name: String? = null,

	val id: String? = null,

	val slug: String? = null
)

data class ProductCategoryEntity(

	val image: String? = null,

	val name: String? = null,

	val id: String? = null,

	val slug: String? = null
)

data class ProductMetadataEntity(

	val numberOfPages: Int? = null,

	val nextPage: Int? = null,

	val limit: Int? = null,

	val currentPage: Int? = null
)
