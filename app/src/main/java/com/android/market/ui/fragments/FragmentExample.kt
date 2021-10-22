package com.android.market.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.market.R
import com.android.market.api.Retrofit
import com.android.market.data.Product
import com.android.market.databinding.ExampleFragmentBinding
import com.android.market.databinding.FragmentAddNewProductBinding
import com.android.market.ui.viewModels.DetailViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentExample :Fragment(R.layout.example_fragment) {
    private val binding: ExampleFragmentBinding by lazy { ExampleFragmentBinding.inflate(layoutInflater) }
    private val detailViewModel= DetailViewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailViewModel.getProduct("10")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product=Product("kitap","kitap","asdsad",123,"16","fsdfsddfsds","154.00")
        binding.buttonAddProduct.setOnClickListener{
            val addNewProductFragment = AddNewProductFragment()
            addNewProductFragment.setStyle(
                DialogFragment.STYLE_NORMAL,
                R.style.AppBottomSheetDialogTheme
            )
            addNewProductFragment.show(
                requireActivity().supportFragmentManager,
                "BottomSheetDialog"
            )
            //findNavController().navigate(FragmentExampleDirections.actionFragmentExampleToAddNewProductFragment())
        }
        binding.buttonDetail.setOnClickListener{

            findNavController().navigate(FragmentExampleDirections.actionFragmentExampleToDetailFragment(detailViewModel.product.value!!))
        }

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