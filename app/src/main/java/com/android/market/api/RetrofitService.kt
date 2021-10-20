package com.android.market.api

import com.android.market.data.Product

import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @GET("products")
    fun getAllProducts(): Call<Array<Product>>

    @GET("products/{id}")
    fun getProduct(): Call<Product>

    @POST("/products")
    fun createProduct(
        @Body product: Product
    ): Call<Product>

    @PUT("products/{id}")
    fun updateProduct(@Path("id") id:String,
    @Body product: Product):Call<Product>

    @DELETE("products/{id}")
    fun deleteProduct(@Path("id")id:String):Call<Product>

}