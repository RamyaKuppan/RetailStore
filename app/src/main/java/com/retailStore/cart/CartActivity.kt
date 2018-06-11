package com.retailStore.cart

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.retailStore.BaseActivity
import com.retailStore.R
import com.retailStore.cart.data.Cart
import com.retailStore.cart.data.source.CartLocalDataSource
import com.retailStore.cart.data.source.CartRepository
import com.retailStore.database.RetailStoreDatabase
import com.retailStore.databinding.ActivityCartBinding
import com.retailStore.productDetail.ProductDetailActivity

class CartActivity : BaseActivity(), CartOperationListener, CartItemClickListener {

    private lateinit var cartBinding: ActivityCartBinding
    private lateinit var cartItemAdapter: CartItemAdapter
    private lateinit var cartViewModel: CartViewModel

    companion object {
        /**
         * Constant value to assign task for cart operation
         */
        const val TASK_ID_LOAD = 1
        const val TAsk_ID_DELETE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_cart)
        title = "Cart Items"
        showBackButton(true)

        val cartDao = RetailStoreDatabase.getInstance(this).cartDao()
        val cartFactory = CartViewModel.Factory(application, CartRepository(CartLocalDataSource(cartDao)))

        cartViewModel = ViewModelProviders.of(this, cartFactory)
                .get(CartViewModel::class.java)

        cartViewModel.getCartItems(this)

        cartItemAdapter = CartItemAdapter(this)
        cartBinding.cartItems.adapter = cartItemAdapter

    }

    override fun onCartLoad(cartList: ArrayList<Cart>) {
        cartBinding.cartCount.text = "Items: ${cartList.size}"
        cartBinding.cartPrice.text = "Price: $${getTotalPrice(cartList)}"

        if (cartList.size == 0) {
            cartBinding.cartItems.visibility = View.INVISIBLE
        } else {
            cartItemAdapter.setCartItems(cartList)
            cartBinding.cartItems.visibility = View.INVISIBLE
        }

        cartBinding.executePendingBindings()
    }

    override fun onCartDeleted() {
        Toast.makeText(this, "Cart item deleted successfully", Toast.LENGTH_LONG).show()
        cartViewModel.getCartItems(this)
    }

    override fun deleteCart(cartId: Int) {
        Toast.makeText(this, "Cart deleted from Database", Toast.LENGTH_LONG).show()
        cartViewModel.deleteCartItem(this, cartId)

    }

    override fun displayProduct(productId: Int) {
        val intent = Intent(this, ProductDetailActivity::class.java)
        val bundle = Bundle()
        bundle.putInt(ProductDetailActivity.PRODUCT_ID, productId)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    /**
     * Calculate list of cart price
     */
    private fun getTotalPrice(cartList: ArrayList<Cart>): Float {
        var totalPrice = 0.0f
        for (cart in cartList) {
            totalPrice += (cart.price * cart.count)
        }
        return totalPrice
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return false
    }
}
