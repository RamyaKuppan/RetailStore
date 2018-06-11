package com.retailStore.category

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.retailStore.R
import com.retailStore.databinding.ItemCategoryBinding

/**
 * Used to Display the list category
 */
class CategoryListAdapter(private val categoryClickListener: CategoryListListener) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var categories: ArrayList<String> = ArrayList<String>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemCategoryBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.context), R.layout.item_category,
                        parent, false)
        return CategoryViewHolder(binding, categoryClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoryViewHolder).onBind(categories[position])
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    /**
     * Set the category list after fetching from database
     */
    fun setCategoryList(categoryList: ArrayList<String>) {
        categories = categoryList
        notifyDataSetChanged()
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        (holder as? CategoryViewHolder)?.unBind()
    }

    class CategoryViewHolder(private val binding: ItemCategoryBinding, var categoryClickListener: CategoryListListener) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(categoryDisplay: String) {
            binding.categoryString = categoryDisplay
            binding.clickListener = categoryClickListener
            binding.executePendingBindings()

        }

        fun unBind() {
            binding.unbind()
        }
    }
}