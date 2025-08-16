package com.example.domain.entity


data class AddToCartResponseEntity(

    val data: AddToCartDataEntity? = null,

    val numOfCartItems: Int? = null,

    val cartId: String? = null,

    val message: String? = null,

    val status: String? = null
)

data class AddToCartProductsItemEntity(

    val product: String? = null,

    val price: Int? = null,

    val count: Int? = null,

    val id: String? = null
)

data class AddToCartDataEntity(

    val cartOwner: String? = null,

    val createdAt: String? = null,

    val totalCartPrice: Int? = null,

    val v: Int? = null,

    val id: String? = null,

    val products: List<AddToCartProductsItemEntity?>? = null,

    val updatedAt: String? = null
)
