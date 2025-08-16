package com.example.data.mappers

import com.example.data.models.cart.CartBrand
import com.example.data.models.cart.CartCategory
import com.example.data.models.cart.CartData
import com.example.data.models.cart.CartProductsItem
import com.example.data.models.cart.CartResponse
import com.example.data.models.cart.Product
import com.example.domain.entity.CartBrandEntity
import com.example.domain.entity.CartCategoryEntity
import com.example.domain.entity.CartDataEntity
import com.example.domain.entity.CartProductsItemEntity
import com.example.domain.entity.CartResponseEntity
import com.example.domain.entity.ProductEntity

fun CartResponse.toEntity(): CartResponseEntity {
    return CartResponseEntity(cartData?.toEntity(), numOfCartItems, cartId, status)
}

fun CartData.toEntity(): CartDataEntity {
    return CartDataEntity(cartOwner, createdAt, totalCartPrice, v, id, products?.map {
        it?.toEntity()
    }, updatedAt)
}

fun CartProductsItem.toEntity(): CartProductsItemEntity {
    return CartProductsItemEntity(product?.toEntity(), price, count, id)
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(quantity, imageCover, _ProductId, CartId, subcategory?.map {
        it?.toEntity()
    }, title, cartCategory?.toEntity(), cartBrand?.toEntity(), ratingsAverage)
}

fun CartCategory.toEntity(): CartCategoryEntity {
    return CartCategoryEntity(image, name, id, slug)
}

fun CartBrand.toEntity(): CartBrandEntity {
    return CartBrandEntity(image, name, id, slug)
}