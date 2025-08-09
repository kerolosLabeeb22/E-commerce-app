package com.example.data.dataSource.utils

import com.example.data.mappers.tonEntity
import com.example.data.models.errors.ErrorResponseModel
import com.example.domain.Utils.ApiResult
import com.example.domain.errors.ConnectionError
import com.example.domain.errors.NoDataError
import com.example.domain.errors.ServerError
import com.google.gson.Gson
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

suspend fun <T> safeApiCalls(api: suspend () -> Response<T>): ApiResult<T> {
    try {
        val response = api.invoke()
        if (response.isSuccessful) {
            return if (response.body() != null)
                ApiResult.Success(response.body()!!)
            else
                ApiResult.Error(NoDataError("No Data Received"))
        } else {
            val json = response.errorBody()?.string()
            val gson = Gson()
            val errorResponse = gson.fromJson(json, ErrorResponseModel::class.java)
            return ApiResult.Error(
                ServerError(
                    errorResponse.errors?.msg ?: errorResponse.message ?: ""
                )
            )
        }
    } catch (e: Exception) {
        return when (e) {
            is UnknownHostException, is IOException, is TimeoutException -> {
                ApiResult.Error(ConnectionError("Check your internet connection"))
            }

            else -> ApiResult.Error(e)
        }
    }
}

fun <T,R> ApiResult<T>.mapResource(transform: (T) -> R) : ApiResult<R> {
    return when(this) {
        is ApiResult.Error -> ApiResult.Error(exception)
        is ApiResult.Success -> ApiResult.Success(transform(data))
    }


}