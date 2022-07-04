package com.turtlemint.issues.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Network {
    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/repos/square/okhttp/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApiService(): ApiService = retrofit.create(ApiService::class.java)
}