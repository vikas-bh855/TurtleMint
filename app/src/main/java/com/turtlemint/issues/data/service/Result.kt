package com.turtlemint.issues.data.service

sealed class Result {
    data class Success<T>(val data: T) : Result()
    data class Error<T>(val data: T) : Result()
}