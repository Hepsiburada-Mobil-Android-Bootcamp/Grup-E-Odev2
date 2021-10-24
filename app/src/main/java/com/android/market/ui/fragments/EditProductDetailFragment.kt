package com.android.market.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.market.R
import com.android.market.data.Product
import com.android.market.databinding.FragmentEditDetailBinding
import com.android.market.ui.viewModels.EditProductViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_edit_detail.*

class EditProductDetailFragment : Fragment() {
    private val binding : FragmentEditDetailBinding by lazy { FragmentEditDetailBinding.inflate(layoutInflater) }
    private val editProductViewModel = EditProductViewModel()
    private val args: DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setDetails()
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
            }
        }
        binding.buttonApply.setOnClickListener{
            updateProduct()
        }


    }
    private fun updateProduct(){
        if(isNull()){
            editProductViewModel.updateProduct(
                Product(
                productName = binding.textFieldProductName.editText?.text.toString(),
                category = binding.textFieldCategory.text.toString(),
                url = binding.textFieldUrl.editText?.text.toString(),
                stock = binding.textFieldStock.editText?.text.toString().toInt(),
                detail = binding.textFieldDetail.editText?.text.toString(),
                id = editProductViewModel.product.value!!.id,
                price = binding.textFieldPrice.editText?.text.toString()
            )
            )
        }
        findNavController().navigate(EditProductDetailFragmentDirections.actionEditProductDetailFragmentToDetailFragment(editProductViewModel.product.value!!))
    }

    fun setDetails() {
        editProductViewModel.setProduct(args.product)
        editProductViewModel.product.observe(viewLifecycleOwner,{
                textFieldProductName.editText?.setText(editProductViewModel.product.value?.productName.toString())
                categorySpinner.post {
                    categorySpinner.setSelection(resources.getStringArray(R.array.category).toList().indexOf(editProductViewModel.product.value?.category.toString()))
                }
                textFieldUrl.editText?.setText(editProductViewModel.product.value?.url.toString())
                textFieldStock.editText?.setText(editProductViewModel.product.value?.stock.toString())
                textFieldDetail.editText?.setText(editProductViewModel.product.value?.detail)
                textFieldPrice.editText?.setText(editProductViewModel.product.value?.price)

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