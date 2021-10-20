package com.android.market.api

import com.android.market.data.Product

import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @GET("products")
    fun getAllCharacters(): Call<Array<Product>>
    @POST("/products")
    fun createProduct(
        @Body product: Product
    ): Call<Product>
    @FormUrlEncoded
    @PUT("products/{id}")
    fun updateProduct(@Path("id") id:String,
    @Field("name")name:String):Call<Product>

}