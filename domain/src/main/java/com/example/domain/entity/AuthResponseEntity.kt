package com.example.domain.entity

data class AuthResponseEntity(
	val message: String? = null,
	val userEntity: UserEntity? = null,
	val token: String? = null
)

data class UserEntity(
	val role: String? = null,
	val name: String? = null,
	val email: String? = null
)

