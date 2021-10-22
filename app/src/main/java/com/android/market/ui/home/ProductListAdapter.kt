package com.android.market.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.android.market.R
import com.android.market.data.Product
import com.android.market.databinding.ItemCategoriesListCardBinding
import com.android.market.databinding.ItemProductListCardBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class ProductListAdapter (private val productsList:List<Product>) :
    RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>(),
    Filterable{

    private var _binding: ItemProductListCardBinding? = null
    private val binding get() = _binding!!
    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_list_card, parent, false)
        _binding= DataBindingUtil.bind(view)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductListAdapter.ProductViewHolder, position: Int) {

        Glide.with(binding.imgProduct)
            .asBitmap()
            .load(productsList[position].url)
            .into(binding.imgProduct)
        binding.productName.text = productsList[position].productName
    }


    override fun getItemCount(): Int = productsList.size

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }

}