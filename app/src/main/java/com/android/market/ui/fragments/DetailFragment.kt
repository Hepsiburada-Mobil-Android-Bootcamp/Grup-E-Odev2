package com.android.market.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.market.databinding.FragmentDetailBinding
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.market.R
import com.android.market.ui.viewModels.DetailViewModel
import com.bumptech.glide.Glide


class DetailFragment : Fragment() {
    private val binding : FragmentDetailBinding by lazy { FragmentDetailBinding.inflate(layoutInflater) }
    private val detailViewModel= DetailViewModel()
    private val args: DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel.getProduct(args.product.id)
        setDetails()
        binding.deleteButton.setOnClickListener{
            val diaBox = askDelete()
            diaBox!!.show()
        }
        binding.editButton.setOnClickListener{
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToEditProductDetailFragment(args.product))
        }

    }

    private fun setDetails() {
        detailViewModel.product.observe(viewLifecycleOwner,{
            Glide.with(binding.productImage)
                .asBitmap()
                .load(detailViewModel.product.value?.url)
                .into(binding.productImage)
            binding.apply {
                categoryText.text=detailViewModel.product.value?.category
                productName.text=detailViewModel.product.value?.productName
                productDetail.text=detailViewModel.product.value?.detail
                textPrice.text=detailViewModel.product.value?.price
                textStock.text=detailViewModel.product.value?.stock.toString()
            }
        })
    }

    //Delete Dialog
    private fun askDelete(): AlertDialog? {
        return view?.let {
            AlertDialog.Builder(it.context)
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setIcon(R.drawable.ic_delete)
                .setPositiveButton("Delete",
                    DialogInterface.OnClickListener { dialog, whichButton ->
                        findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToFragmentExample())
                        detailViewModel.deleteProduct(args.product.id)
                    })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
                .create()
        }
    }
}