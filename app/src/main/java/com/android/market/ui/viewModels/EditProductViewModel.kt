package com.android.market.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.market.api.Retrofit
import com.android.market.data.Product


class EditProductViewModel:ViewModel() {
    private val _product = MutableLiveData<Product>()
    var product: LiveData<Product> = _product

    fun updateProduct(productItem: Product){
        Retrofit.update(productItem)
    }
    fun setProduct(product: Product){
        _product.value=product
    }
}