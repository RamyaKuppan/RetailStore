package com.retailStore.cart.data.source

import com.retailStore.cart.data.Cart

interface CartDataSource {
    interface CallBack {
        fun onSuccess(cartList: ArrayList<Cart>)

        fun onFailure(message: String)
    }

    /**
     * Get the Cart items from [RetailStoreDatabase]
     * calls [CartDataSource.onSuccess] when receives item from database
     *
     */
    fun getCartItems(callBack: CallBack)
}