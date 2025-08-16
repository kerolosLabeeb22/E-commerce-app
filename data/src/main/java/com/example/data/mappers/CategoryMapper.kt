package com.example.data.mappers

import com.example.data.models.cateogry.CategoryDataItem
import com.example.data.models.cateogry.CategoryMetadata
import com.example.data.models.cateogry.CategoryResponse
import com.example.domain.entity.CategoryDataItemEntity
import com.example.domain.entity.CategoryMetadataEntity
import com.example.domain.entity.CategoryResponseEntity

fun CategoryResponse.toEntity(): CategoryResponseEntity {
    return CategoryResponseEntity(metadata?.toEntity(), data?.map { it!!.toEntity() }, results)
}


fun CategoryMetadata.toEntity(): CategoryMetadataEntity {
    return CategoryMetadataEntity(numberOfPages, limit, currentPage)
}

fun CategoryDataItem.toEntity(): CategoryDataItemEntity {
    return CategoryDataItemEntity(image, createdAt, name, id, slug, updatedAt)
}