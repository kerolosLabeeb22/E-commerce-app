package com.example.data.mappers

import com.example.data.models.auth.AuthResponseModel
import com.example.data.models.auth.UserModel
import com.example.domain.entity.AuthResponseEntity
import com.example.domain.entity.UserEntity

fun AuthResponseModel.tonEntity() : AuthResponseEntity{
    return AuthResponseEntity(message,userModel?.toEntity(),token)
}


fun UserModel.toEntity() : UserEntity{
    return UserEntity(role,name,email)
}