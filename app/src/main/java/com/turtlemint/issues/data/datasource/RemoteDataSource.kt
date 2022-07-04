package com.turtlemint.issues.data.datasource

import com.turtlemint.issues.data.service.ApiService
import retrofit2.Response

open class RemoteDataSource(private val apiService: ApiService) {

    open suspend fun fetchIssues() = fetchApiCalls { apiService.fetchIssue() }

    open suspend fun fetchComments(id: Int) = fetchApiCalls { apiService.fetchComments(id) }

    private suspend fun <T> fetchApiCalls(call: suspend () -> Response<T>): Result {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                when (response.code()) {
                    in 200..400 -> {
                        // success
                        Result.Success(response.body())
                    }
                    in 400..499 -> {
                        // Validation failed with the request
                        Result.Error(response.code(), "Validation Failed")
                    }
                    else -> {
                        //Server Error
                        Result.Error(response.code(), "Server Error")
                    }
                }
            } else {
                // Error
                Result.Error(response.code(), "Unknown Error")
            }
        } catch (e: Exception) {
            // Network failure
            Result.Error(404, "Failed to connect")
        }
    }
}

sealed class Result {
    data class Success<T>(val data: T) : Result()
    data class Error<T>(val code: Int, val data: T) : Result()
}