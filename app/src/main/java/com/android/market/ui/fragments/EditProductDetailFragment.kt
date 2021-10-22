package com.android.market.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.android.market.R
import com.android.market.databinding.FragmentEditDetailBinding
import com.android.market.ui.viewModels.EditProductViewModel
import com.bumptech.glide.Glide

class EditProductDetailFragment : Fragment() {
    private val binding : FragmentEditDetailBinding by lazy { FragmentEditDetailBinding.inflate(layoutInflater) }
    private val editProductViewModel = EditProductViewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Spinner Adapter
        ArrayAdapter.createFromResource(view.context, R.array.category,android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.categorySpinner.adapter = adapter
        }
        binding.categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2>0){ binding.textFieldCategory.text=p0?.getItemAtPosition(p2).toString()}
                else{
                    binding.textFieldCategory.text=null
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
       fun setDetails() {
            editProductViewModel.product.observe(viewLifecycleOwner,{
//                Glide.with(binding.productImage)
//                    .asBitmap()
//                    .load(detailViewModel.product.value?.url)
//                    .into(binding.productImage)
//                binding.apply {
//                    categoryText.text=detailViewModel.product.value?.category
//                    productName.text=detailViewModel.product.value?.productName
//                    productDetail.text=detailViewModel.product.value?.detail
//                    textPrice.text=detailViewModel.product.value?.price
//                    textStock.text=detailViewModel.product.value?.stock.toString()
//                }
            })
        }
    }
}