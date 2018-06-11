package com.retailStore.cart

interface CartItemClickListener {
    /**
     * Delete the cart from database
     */
    fun deleteCart(cartId: Int)

    /**
     * Re-direct to Product Details screen
     */
    fun displayProduct(productId: Int)
}