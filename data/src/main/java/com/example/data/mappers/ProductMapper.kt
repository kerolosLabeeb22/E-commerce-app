package com.example.data.mappers

import com.example.data.models.cart.CartSubcategoryItem
import com.example.data.models.product.ProductDataItem
import com.example.data.models.product.ProductsResponse
import com.example.domain.entity.CartSubcategoryItemEntity
import com.example.domain.entity.ProductDataItemEntity
import com.example.domain.entity.ProductsResponseEntity

fun ProductsResponse.toEntity(): ProductsResponseEntity {
    return ProductsResponseEntity(categoryMetadata?.toEntity(), data?.map {
        it?.toEntity()
    }, results)
}

fun ProductDataItem.toEntity(): ProductDataItemEntity {
    return ProductDataItemEntity(
        sold,
        images,
        quantity,
        availableColors,
        imageCover,
        description,
        title,
        ratingsQuantity,
        ratingsAverage,
        createdAt,
        price,
        _id,
        id,
        subcategory?.map {
            it?.toEntity()
        },
        category?.toEntity(),
        brand?.toEntity(),
        slug,
        updatedAt,
        priceAfterDiscount
    )
}

fun CartSubcategoryItem.toEntity(): CartSubcategoryItemEntity {
    return CartSubcategoryItemEntity(name, id, category, slug)
}