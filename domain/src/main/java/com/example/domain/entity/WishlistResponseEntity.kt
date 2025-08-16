package com.example.domain.entity


data class WishlistResponseEntity(

    val data: List<WishDataItemEntity?>? = null,

    val count: Int? = null,

    val status: String? = null
)

data class WishSubcategoryItemEntity(

    val name: String? = null,

    val id: String? = null,

    val category: String? = null,

    val slug: String? = null
)

data class WishlistCategoryEntity(

    val image: String? = null,

    val name: String? = null,

    val id: String? = null,

    val slug: String? = null
)

data class WishBrandEntity(

    val image: String? = null,

    val name: String? = null,

    val id: String? = null,

    val slug: String? = null
)

data class WishDataItemEntity(

    val sold: Int? = null,

    val images: List<String?>? = null,

    val quantity: Int? = null,

    val imageCover: String? = null,

    val description: String? = null,

    val title: String? = null,

    val ratingsQuantity: Int? = null,

    val ratingsAverage: Any? = null,

    val createdAt: String? = null,

    val price: Int? = null,

    val v: Int? = null,

    val _wishid: String? = null,

    val wishId: String? = null,

    val subcategory: List<WishSubcategoryItemEntity?>? = null,

    val category: ProductCategoryEntity? = null,

    val brand: ProductBrandEntity? = null,

    val slug: String? = null,

    val updatedAt: String? = null,

    val availableColors: List<Any?>? = null
)
