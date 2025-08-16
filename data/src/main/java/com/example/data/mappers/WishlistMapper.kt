package com.example.data.mappers


import com.example.data.models.product.ProductBrand
import com.example.data.models.product.ProductCategory
import com.example.data.models.wishlist.AddToWishlistResponse
import com.example.data.models.wishlist.RemoveFromWishlistResponse
import com.example.data.models.wishlist.WishDataItem
import com.example.data.models.wishlist.WishSubcategoryItem
import com.example.data.models.wishlist.WishlistResponse
import com.example.domain.entity.AddToWishlistResponseEntity
import com.example.domain.entity.ProductBrandEntity
import com.example.domain.entity.ProductCategoryEntity
import com.example.domain.entity.RemoveFromWishlistResponseEntity
import com.example.domain.entity.WishDataItemEntity
import com.example.domain.entity.WishSubcategoryItemEntity
import com.example.domain.entity.WishlistResponseEntity
import kotlin.collections.map

fun RemoveFromWishlistResponse.toEntity(): RemoveFromWishlistResponseEntity {
    return RemoveFromWishlistResponseEntity(data, message, status)
}
fun WishlistResponse.toEntity(): WishlistResponseEntity {
    return WishlistResponseEntity(data!!.map { it?.toEntity() }, count, status)
}

fun WishDataItem.toEntity(): WishDataItemEntity {
    return WishDataItemEntity(
        sold,
        images,
        quantity,
        imageCover,
        description,
        title,
        ratingsQuantity,
        ratingsAverage,
        createdAt,
        price,
        v,
        _wishid,
        wishId,
        wishlistSubcategory?.map {
            it!!.toEntity()
        },
        wishCategory?.toEntity(),
        wishlistBrand?.toEntity(),
        slug,
        updatedAt,
        availableColors
    )
}

fun WishSubcategoryItem.toEntity(): WishSubcategoryItemEntity {
    return WishSubcategoryItemEntity(name, id, category, slug)
}

fun ProductCategory.toEntity(): ProductCategoryEntity {
    return ProductCategoryEntity(image, name, id, slug)
}

fun ProductBrand.toEntity(): ProductBrandEntity {
    return ProductBrandEntity(image, name, id, slug)
}

fun AddToWishlistResponse.toEntity(): AddToWishlistResponseEntity {
    return AddToWishlistResponseEntity(data, message, status)
}