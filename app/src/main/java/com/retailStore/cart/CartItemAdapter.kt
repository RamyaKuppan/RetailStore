package com.retailStore.cart

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.retailStore.R
import com.retailStore.cart.data.Cart
import com.retailStore.databinding.ItemCartBinding

class CartItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var cartItems: ArrayList<Cart> = ArrayList<Cart>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemCartBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.context), R.layout.item_cart,
                        parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CartViewHolder).onBind(cartItems[position])
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }


    fun setCartItems(cartItem: ArrayList<Cart>) {
        this.cartItems = cartItem
        notifyDataSetChanged()
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        (holder as? CartViewHolder)?.unBind()
    }

    class CartViewHolder(private val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(cart: Cart) {
            binding.cart = cart
            binding.executePendingBindings()

        }

        fun unBind() {
            binding.unbind()
        }
    }

}