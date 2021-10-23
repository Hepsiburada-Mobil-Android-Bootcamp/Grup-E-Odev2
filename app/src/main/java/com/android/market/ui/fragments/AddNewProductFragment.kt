package com.android.market.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast

import com.android.market.R
import com.android.market.data.Product
import com.android.market.databinding.FragmentAddNewProductBinding
import com.android.market.ui.viewModels.AddNewProductViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddNewProductFragment : BottomSheetDialogFragment() {
    private val binding: FragmentAddNewProductBinding by lazy { FragmentAddNewProductBinding.inflate(layoutInflater) }
    private val addNewProductViewModel=AddNewProductViewModel()

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
        ArrayAdapter.createFromResource(view.context,R.array.category,android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
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
        binding.buttonAdd.setOnClickListener{
            if(isNull()){
                addNewProductViewModel.addProduct(Product(
                    productName = binding.textFieldProductName.editText?.text.toString(),
                    category = binding.textFieldCategory.text.toString(),
                    url = binding.textFieldUrl.editText?.text.toString(),
                    stock = binding.textFieldStock.editText?.text.toString().toInt(),
                    detail = binding.textFieldDetail.editText?.text.toString(),
                    id = "",
                    price = binding.textFieldPrice.editText?.text.toString()
                ))
            }
        }
        addNewProductViewModel.product.observe(viewLifecycleOwner,{
            Toast.makeText(context,"Product added successfully",Toast.LENGTH_SHORT)
            dismiss()
        })
    }

    private fun isNull():Boolean {
        if(
            binding.textFieldProductName.editText?.text.isNullOrEmpty()&&
            binding.textFieldCategory.text.isNullOrEmpty()&&
            binding.textFieldUrl.editText?.text.isNullOrEmpty()&&
            binding.textFieldStock.editText?.text.isNullOrEmpty()&&
            binding.textFieldDetail.editText?.text.isNullOrEmpty()&&
            binding.textFieldPrice.editText?.text.isNullOrEmpty()
        ){return false}
        return true
    }
}