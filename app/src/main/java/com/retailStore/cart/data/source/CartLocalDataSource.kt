package com.retailStore.cart.data.source

import com.retailStore.cart.data.Cart
import org.jetbrains.anko.doAsync

class CartLocalDataSource(var cartDao: CartDao) : CartDataSource {

    override fun getCartItems(taskId: Int, callBack: CartDataSource.CallBack) {

        doAsync {
            val cartItems = cartDao.getCartItems() as ArrayList<Cart>
            callBack.onSuccess(taskId, cartItems)
        }
    }

    override fun deleteCartItem(taskId: Int, cartId: Int, callBack: CartDataSource.CallBack) {

        doAsync {
            cartDao.deleteCart(cartId)
            val cartItems = cartDao.getCartItems() as ArrayList<Cart>

            callBack.onSuccess(taskId, cartItems)
        }
    }


}