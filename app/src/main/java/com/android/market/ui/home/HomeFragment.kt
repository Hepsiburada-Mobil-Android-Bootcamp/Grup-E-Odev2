package com.android.market.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.android.market.R
import com.android.market.databinding.FragmentHomePageBinding
import com.android.market.ui.fragments.AddNewProductFragment
import java.lang.reflect.Array

class HomeFragment:Fragment() {
    val binding  : FragmentHomePageBinding  by lazy { FragmentHomePageBinding.inflate(layoutInflater) }
    private val viewmodel = HomeViewModel()
    //private val array: Array = resources.getStringArray(R.array.category)
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
            binding.recycler1.adapter=CategoryListAdapter(resources.getStringArray(R.array.categoryNames).toList(),viewmodel)

            val list:List<Product> = viewmodel.product.value as List<Product>
            binding.recycler2.adapter=ProductListAdapter(list).also {
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