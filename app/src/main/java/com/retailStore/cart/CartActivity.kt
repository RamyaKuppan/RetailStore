package com.retailStore.cart

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.retailStore.R
import com.retailStore.cart.data.Cart
import com.retailStore.cart.data.source.CartLocalDataSource
import com.retailStore.cart.data.source.CartRepository
import com.retailStore.database.RetailStoreDatabase
import com.retailStore.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity(), CartItemListener {

    private lateinit var cartBinding: ActivityCartBinding
    private lateinit var cartItemAdapter: CartItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_cart)
        title = "Cart Items"

        val cartDao = RetailStoreDatabase.getInstance(this).cartDao()
        val cartFactory = CartViewModel.Factory(application, CartRepository(CartLocalDataSource(cartDao)))

        val cartViewModel = ViewModelProviders.of(this, cartFactory)
                .get(CartViewModel::class.java)

        cartViewModel.getCartItems(this)

        cartItemAdapter = CartItemAdapter()
        cartBinding.cartItems.adapter = cartItemAdapter

    }

    override fun onCartLoad(cartList: ArrayList<Cart>) {
        cartBinding.cartCount.text = "Items: ${cartList.size}"
        cartBinding.cartPrice.text = "Price: $${getTotalPrice(cartList)}"
        cartItemAdapter.setCartItems(cartList)
    }

    private fun getTotalPrice(cartList: ArrayList<Cart>): Float {
        var totalPrice = 0.0f
        for (cart in cartList) {
            totalPrice += (cart.price * cart.count)
        }
        return totalPrice
    }

}
