package com.android.market.ui.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.android.market.data.Product
import com.android.market.databinding.FragmentHomePageBinding
import com.bumptech.glide.load.engine.Resource

class HomeFragment:Fragment() {
    val binding  : FragmentHomePageBinding  by lazy { FragmentHomePageBinding.inflate(layoutInflater) }
    val viewmodel = HomeViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //_binding = FragmentSecondBinding.inflate(inflater,container,false)
        val view = binding.root

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewmodel.productlist()
        viewmodel.product.observe(viewLifecycleOwner,{
            binding.recycler2.adapter=ProductListAdapter(it)
        })
        viewmodel.product.observe(viewLifecycleOwner,{
            binding.recycler1.adapter=CategoryListAdapter(it)
        })

    }

}