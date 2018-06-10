package com.retailStore.cart.data.source

class CartRepository(private var cartDataSource: CartDataSource) {

    /**
     * Get the cartItems from Database
     */
    fun getCartItems(taskId:Int,callBack: CartDataSource.CallBack) {
        cartDataSource.getCartItems(taskId,callBack)
    }

    fun deleteCart(taskId: Int,cartId:Int,callBack: CartDataSource.CallBack)
    {
        cartDataSource.deleteCartItem(taskId,cartId,callBack)
    }
}