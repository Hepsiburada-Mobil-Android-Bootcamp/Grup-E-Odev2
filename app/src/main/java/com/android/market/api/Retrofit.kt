package com.android.market.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.market.data.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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




    fun update(product: Product) {
        service.updateProduct("${product.id}", product).enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {

            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                println(t.message)
            }
        })
    }
}