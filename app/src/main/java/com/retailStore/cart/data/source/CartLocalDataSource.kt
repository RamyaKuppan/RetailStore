package com.retailStore.cart.data.source

import com.retailStore.cart.data.Cart
import org.jetbrains.anko.doAsync

class CartLocalDataSource(var cartDao: CartDao) : CartDataSource {


    override fun getCartItems(callBack: CartDataSource.CallBack) {

        doAsync {
            val cartItems = cartDao.getCartItems() as ArrayList<Cart>
            callBack.onSuccess(cartItems)
        }
    }
}