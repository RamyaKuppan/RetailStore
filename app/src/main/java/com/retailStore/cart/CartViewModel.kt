package com.retailStore.cart

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.retailStore.cart.data.Cart
import com.retailStore.cart.data.source.CartDataSource
import com.retailStore.cart.data.source.CartRepository

class CartViewModel(application: Application, var cartRepository: CartRepository) : AndroidViewModel(application), CartDataSource.CallBack {

    private lateinit var cartItemListener: CartItemListener

    /**
     * Get the cartItems from [CartRepository]
     */
    fun getCartItems(cartItemListener: CartItemListener) {
        this.cartItemListener = cartItemListener
        cartRepository.getCartItems(this)
    }

    /**
     * Used to send the cartItem List to [CartActivity]
     */
    override fun onSuccess(cartList: ArrayList<Cart>) {
        cartItemListener.onCartLoad(cartList)
    }

    override fun onFailure(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class Factory(private val application: Application, private val cartRepository: CartRepository) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Application::class.java, CartRepository::class.java).newInstance(application, cartRepository)
        }
    }
}