package com.example.domain.entity


data class CartResponseEntity(

    val cartData: CartDataEntity? = null,

    val numOfCartItems: Int? = null,

    val cartId: String? = null,

    val status: String? = null
)

data class CartSubcategoryItemEntity(
    val name: String? = null,

    val id: String? = null,

    val category: String? = null,

    val slug: String? = null
)

data class CartBrandEntity(

    val image: String? = null,

    val name: String? = null,

    val id: String? = null,

    val slug: String? = null
)

data class CartDataEntity(

    val cartOwner: String? = null,

    val createdAt: String? = null,

    val totalCartPrice: Int? = null,

    val v: Int? = null,

    val id: String? = null,

    val products: List<CartProductsItemEntity?>? = null,

    val updatedAt: String? = null
)

data class CartCategoryEntity(

    val image: String? = null,

    val name: String? = null,

    val id: String? = null,

    val slug: String? = null
)

data class ProductEntity(

    val quantity: Int? = null,

    val imageCover: String? = null,

    val _ProductId: String? = null,

    val CartId: String? = null,

    val subcategory: List<CartSubcategoryItemEntity?>? = null,

    val title: String? = null,

    val cartCategory: CartCategoryEntity? = null,

    val cartBrand: CartBrandEntity? = null,

    val ratingsAverage: Any? = null
)

data class CartProductsItemEntity(

    val product: ProductEntity? = null,

    val price: Int? = null,

    val count: Int? = null,

    val id: String? = null
)
