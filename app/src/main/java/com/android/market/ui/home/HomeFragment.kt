package com.android.market.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.android.market.R
import com.android.market.data.Product
import com.android.market.databinding.FragmentHomePageBinding
import com.android.market.ui.fragments.AddNewProductFragment

class HomeFragment:Fragment() {

    val binding  : FragmentHomePageBinding  by lazy { FragmentHomePageBinding.inflate(layoutInflater) }
    private val viewModel = HomeViewModel()
    var list:List<Product> = listOf()
    private var categoryList = emptyList<String>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        categoryList=resources.getStringArray(R.array.categoryNames).toList()
        viewModel.productList()
        viewModel.product.observe(viewLifecycleOwner,{
            binding.recycler1.adapter=CategoryListAdapter(categoryList,viewModel)

            list = viewModel.product.value as List<Product>
            binding.recycler2.adapter=ProductListAdapter(list,viewModel,categoryList).also {
                it.notifyDataSetChanged()
            }
        })

        binding.addButton.setOnClickListener{
            val addNewProductFragment = AddNewProductFragment()
            addNewProductFragment.setStyle(
                DialogFragment.STYLE_NORMAL,
                R.style.AppBottomSheetDialogTheme
            )
            addNewProductFragment.show(
                requireActivity().supportFragmentManager,
                "BottomSheetDialog"
            )
        }

    }

}