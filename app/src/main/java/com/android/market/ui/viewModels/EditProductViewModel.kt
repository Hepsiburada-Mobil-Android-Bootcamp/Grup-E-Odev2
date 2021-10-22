package com.android.market.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.market.api.Retrofit
import com.android.market.data.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProductViewModel:ViewModel() {
    private val _product = MutableLiveData<Product>()
    var product: LiveData<Product> = _product
    fun updateProduct(productItem: Product){
        Retrofit.service.updateProduct(productItem.id,productItem).enqueue(object: Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if (response.isSuccessful){
                    _product.value=response.body()
                    product=_product
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                println(t.message)
            }
        })
    }
}