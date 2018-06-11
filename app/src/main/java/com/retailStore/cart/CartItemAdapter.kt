package com.retailStore.cart

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.retailStore.R
import com.retailStore.cart.data.Cart
import com.retailStore.databinding.ItemCartBinding

/**
 * Adapter to set for cart list items
 */
class CartItemAdapter(private var cartItemClickListener: CartItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var cartItems: ArrayList<Cart> = ArrayList()

    private lateinit var binding: ItemCartBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemCartBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.context), R.layout.item_cart,
                        parent, false)
        return CartViewHolder(binding, cartItemClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CartViewHolder).onBind(cartItems[position])
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }


    fun setCartItems(cartItem: ArrayList<Cart>) {
        this.cartItems = cartItem
        binding.executePendingBindings()
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        (holder as? CartViewHolder)?.unBind()
    }

    class CartViewHolder(private val binding: ItemCartBinding, private val cartItemClickListener: CartItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(cart: Cart) {
            binding.cart = cart
            binding.clickListener = cartItemClickListener
            binding.executePendingBindings()

        }

        fun unBind() {
            binding.unbind()
        }
    }

}