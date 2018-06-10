package com.retailStore.cart.data.source

import com.retailStore.cart.data.Cart

interface CartDataSource {
    interface CallBack {
        fun onSuccess(taskId: Int, cartList: ArrayList<Cart>)

        fun onFailure(taskId: Int, message: String)
    }

    /**
     * Get the Cart items from [RetailStoreDatabase]
     * calls [CartDataSource.onSuccess] when receives item from database
     *
     */
    fun getCartItems(taskId: Int, callBack: CallBack)

    fun deleteCartItem(taskId: Int, cartId: Int, callBack: CallBack)
}