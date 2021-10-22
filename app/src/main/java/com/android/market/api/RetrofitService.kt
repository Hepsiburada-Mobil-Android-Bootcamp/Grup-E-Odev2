package com.android.market.api

import com.android.market.data.Product


import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
const val BASE_URL ="https://616f2161715a630017b39b39.mockapi.io/api/"
interface RetrofitService {

    //ürün adına göre arama yapma
    @GET("products")
    fun searchProducts(@Query( "name") searchText: String): Call<Product>

    @GET("products")
    fun getAllProducts(): Call<List<Product>>

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