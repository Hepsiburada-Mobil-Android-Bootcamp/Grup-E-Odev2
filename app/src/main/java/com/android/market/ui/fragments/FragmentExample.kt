package com.android.market.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.market.R
import com.android.market.api.Retrofit
import com.android.market.data.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentExample :Fragment(R.layout.example_fragment) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product=Product("deneme","kitap","asdsad",123,"16","fsdfsddfsds","154.00")
        put("3",product)

    }
}

    fun post(product: Product){
        Retrofit.service.createProduct(product).enqueue(object:Callback<Product>{
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                println(response.body())
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                println(t.message)
            }
        })
}
    fun put(id:String,product: Product){

        Retrofit.service.updateProduct("$id",product).enqueue(object:Callback<Product>{
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                println(response.body())
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                println(t.message)
            }

        })
    fun delete(id:String) {
        Retrofit.service.deleteProduct("$id").enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                println(response.body())
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                println(t.message)
            }

        })
    }
}