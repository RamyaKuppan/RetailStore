package com.retailStore.favorite.data

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.retailStore.R
import com.retailStore.databinding.ItemCartBinding
import com.retailStore.databinding.LayoutFavoritelistBinding

class FavoriteListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var favoriteItem: ArrayList<FavoriteItem> = ArrayList()

    private lateinit var binding: ItemCartBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: LayoutFavoritelistBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.context), R.layout.layout_favoritelist,
                        parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FavoriteViewHolder).onBind(favoriteItem[position])
    }

    override fun getItemCount(): Int {
        return favoriteItem.size
    }


    fun setCartItems(favoriteItem: ArrayList<FavoriteItem>) {
        this.favoriteItem = favoriteItem
        binding.executePendingBindings()
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        (holder as? FavoriteViewHolder)?.unBind()
    }

    class FavoriteViewHolder(private val binding: LayoutFavoritelistBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(favoriteItem: FavoriteItem) {
            binding.favorite = favoriteItem
            binding.executePendingBindings()

        }

        fun unBind() {
            binding.unbind()
        }
    }

}