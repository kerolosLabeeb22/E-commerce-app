package com.example.data.mappers

import com.example.data.models.cateogry.CategoryDataItem
import com.example.data.models.cateogry.CategoryMetadata
import com.example.data.models.cateogry.CategoryResponse
import com.example.data.models.cateogry.SubCategoryDataItem
import com.example.data.models.cateogry.SubCategoryMetadata
import com.example.data.models.cateogry.SubCategoryResponse
import com.example.domain.entity.CategoryDataItemEntity
import com.example.domain.entity.CategoryMetadataEntity
import com.example.domain.entity.CategoryResponseEntity
import com.example.domain.entity.SubCategoryDataItemEntity
import com.example.domain.entity.SubCategoryMetadataEntity
import com.example.domain.entity.SubCategoryResponseEntity

fun CategoryResponse.toEntity(): CategoryResponseEntity {
    return CategoryResponseEntity(metadata?.toEntity(), data?.map { it!!.toEntity() }, results)
}


fun CategoryMetadata.toEntity(): CategoryMetadataEntity {
    return CategoryMetadataEntity(numberOfPages, limit, currentPage)
}

fun CategoryDataItem.toEntity(): CategoryDataItemEntity {
    return CategoryDataItemEntity(image, createdAt, name, id, slug, updatedAt)
}

fun SubCategoryResponse.toEntity(): SubCategoryResponseEntity {
    return SubCategoryResponseEntity(
        subCategoryMetadata?.toEntity(),
        data?.map { it?.toEntity() },
        results
    )
}

fun SubCategoryMetadata.toEntity(): SubCategoryMetadataEntity {
    return SubCategoryMetadataEntity(numberOfPages, nextPage, limit, currentPage)
}

fun SubCategoryDataItem.toEntity(): SubCategoryDataItemEntity {
    return SubCategoryDataItemEntity(createdAt, name, id, category, slug, updatedAt)
}