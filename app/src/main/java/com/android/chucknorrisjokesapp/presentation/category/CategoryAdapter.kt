package com.android.chucknorrisjokesapp.presentation.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.chucknorrisjokesapp.databinding.ItemCategoryBinding

class CategoryAdapter(
    private val itemClickListener : (String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val categoryList = ArrayList<String>()

    fun clearList() {
        categoryList.clear()
        notifyDataSetChanged()
    }

    fun setCategoryList(categories: List<String>) {
        categoryList.addAll(categories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position], itemClickListener)
    }

    override fun getItemCount(): Int = categoryList.size

    class CategoryViewHolder(
        private val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: String, itemClickListener: (String) -> Unit) {
            binding.category = category
            binding.root.setOnClickListener {
                itemClickListener(category)
            }
        }

    }

}