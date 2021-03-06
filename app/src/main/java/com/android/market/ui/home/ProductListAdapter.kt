package com.android.market.ui.home


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.market.R
import com.android.market.data.Product
import com.android.market.databinding.ItemProductListCardBinding
import com.bumptech.glide.Glide


class ProductListAdapter (private val productsList:List<Product>,val homeVM:HomeViewModel,private val categoryList:List<String>) :
    RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>(){

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
        //Change color
        when(categoryList.indexOf(homeVM.categoryName)){
            1->binding.itemProductLayout.setBackgroundColor(Color.parseColor("#ffc8ba"))
            2->binding.itemProductLayout.setBackgroundColor(Color.parseColor("#fff1ba"))
            3->binding.itemProductLayout.setBackgroundColor(Color.parseColor("#e3ffba"))
            4->binding.itemProductLayout.setBackgroundColor(Color.parseColor("#c1ffba"))
            5->binding.itemProductLayout.setBackgroundColor(Color.parseColor("#baffe2"))
            6->binding.itemProductLayout.setBackgroundColor(Color.parseColor("#baedff"))
            7->binding.itemProductLayout.setBackgroundColor(Color.parseColor("#c8baff"))
            8->binding.itemProductLayout.setBackgroundColor(Color.parseColor("#f4baff"))
        }

        Glide.with(binding.imgProduct)
            .asBitmap()
            .load(productsList[position].url)
            .into(binding.imgProduct)

        binding.productName.text = productsList[position].productName
        holder.itemView.setOnClickListener{
            it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(productsList[position]))
        }
    }

    // getItemID & getItemViewType has stop recyclerView shuffling
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun getItemCount(): Int = productsList.size


}