package com.android.market.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.market.R
import com.android.market.data.Product

class CategoryListAdapter(private val productsList: List<Product>, val homeVM:HomeViewModel) : RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryListAdapter.CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_categories_list_card, parent, false)
        return CategoryViewHolder(view)
    }
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val myViewModel = productsList[position]

        // sets the text to the textview from our itemHolder class
        holder.title.text = myViewModel.category

        holder.itemView.setOnClickListener{
            homeVM.category(productsList[position].category)
        }
    }

    override fun getItemCount(): Int = productsList.size

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.rw1_image_name)
    }


}