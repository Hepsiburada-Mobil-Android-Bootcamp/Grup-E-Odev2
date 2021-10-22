package com.android.market.ui.home

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.market.api.Retrofit
import com.android.market.data.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel() : ViewModel() {
    private val _productlist = MutableLiveData<List<Product>>()
    var product: LiveData<List<Product>> = _productlist
    fun productlist(){
        Retrofit.service.getAllProducts().enqueue(object : Callback<List<Product>>{
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                _productlist.value = response.body()
                product=_productlist
                println(response.body())
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }
    fun category(categoryName: String){
        Retrofit.service.getAllProducts().enqueue(object : Callback<List<Product>>{
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                _productlist.value=response.body()?.let { filter(categoryName, it) }
                product=_productlist
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun filter(categoryName:String,list: List<Product>):List<Product>{
        return list.filter {
            it.category==categoryName
        }
    }
}