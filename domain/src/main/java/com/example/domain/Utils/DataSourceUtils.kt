package com.example.domain.Utils

import android.os.Message

sealed interface ApiResult<T>{
    data class Success<T>(val data: T) : ApiResult<T>
    data class Error<T>(val exception: Exception ): ApiResult<T>
}