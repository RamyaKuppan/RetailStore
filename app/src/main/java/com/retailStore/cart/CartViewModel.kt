package com.retailStore.cart

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.retailStore.cart.data.Cart
import com.retailStore.cart.data.source.CartDataSource
import com.retailStore.cart.data.source.CartRepository

class CartViewModel(application: Application, private var cartRepository: CartRepository) : AndroidViewModel(application), CartDataSource.CallBack {

    private lateinit var cartOperationListener: CartOperationListener

    /**
     * Get the cartItems from [CartRepository]
     */
    fun getCartItems(cartOperationListener: CartOperationListener) {
        this.cartOperationListener = cartOperationListener
        cartRepository.getCartItems(CartActivity.TASK_ID_LOAD, this)
    }

    fun deleteCartItem(cartOperationListener: CartOperationListener, cartId: Int) {
        this.cartOperationListener = cartOperationListener
        cartRepository.deleteCart(CartActivity.TAsk_ID_DELETE, cartId, this)
    }

    /**
     * Used to send the cartItem List to [CartActivity]
     */
    override fun onSuccess(taskId: Int, cartList: ArrayList<Cart>) {
        if (taskId == CartActivity.TASK_ID_LOAD) {
            cartOperationListener.onCartLoad(cartList)
        } else {
            cartOperationListener.onCartDeleted()
        }
    }

    override fun onFailure(taskId: Int, message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class Factory(private val application: Application, private val cartRepository: CartRepository) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Application::class.java, CartRepository::class.java).newInstance(application, cartRepository)
        }
    }
}