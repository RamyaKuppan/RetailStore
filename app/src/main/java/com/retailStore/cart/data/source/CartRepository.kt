package com.retailStore.cart.data.source

class CartRepository(private var cartDataSource: CartDataSource) {

    /**
     * Get the cartItems from Database
     */
    fun getCartItems(callBack: CartDataSource.CallBack) {
        cartDataSource.getCartItems(callBack)
    }
}