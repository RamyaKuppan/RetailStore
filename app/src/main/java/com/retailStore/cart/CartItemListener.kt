package com.retailStore.cart

import com.retailStore.cart.data.Cart

interface CartItemListener
{
    fun onCartLoad(cartList: ArrayList<Cart>)
}