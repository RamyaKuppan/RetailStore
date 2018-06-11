package com.retailStore.cart

import com.retailStore.cart.data.Cart

interface CartOperationListener {
    /**
     * Get the list cart item from database
     */
    fun onCartLoad(cartList: ArrayList<Cart>)

    /**
     * Delete the cart item
     */
    fun onCartDeleted()
}