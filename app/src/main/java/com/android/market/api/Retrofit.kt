package com.android.market.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private const val BASE_URL = "https://616f2161715a630017b39b39.mockapi.io/api/"

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    val service: RetrofitService = retrofit.create(RetrofitService::class.java)
}