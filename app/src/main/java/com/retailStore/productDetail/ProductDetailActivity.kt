package com.retailStore.productDetail

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.retailStore.BaseActivity
import com.retailStore.R
import com.retailStore.cart.CartActivity
import com.retailStore.cart.data.Cart
import com.retailStore.database.RetailStoreDatabase
import com.retailStore.databinding.ActivityProductDetailBinding
import com.retailStore.productDetail.data.ProductDetailsLocalSource
import com.retailStore.productDetail.data.ProductDetailsRepository
import com.retailStore.productList.data.Product
import org.jetbrains.anko.doAsync

class ProductDetailActivity : BaseActivity(), ProductDetailsListener {

    companion object {
        const val PRODUCT_ID = "Product_ID"

    }

    private lateinit var productDetailsBinding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productDetailsBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_product_detail)
        title = "Product Details Screen"

        showBackButton(true)

        val productId = intent.extras.getInt(PRODUCT_ID)

        val productDao = RetailStoreDatabase.getInstance(application).productDao()

        val detailsFactory = ProductDetailViewModel.Factory(application, ProductDetailsRepository(ProductDetailsLocalSource(productDao)))

        val detailViewModel = ViewModelProviders.of(this, detailsFactory)
                .get(ProductDetailViewModel::class.java)

        detailViewModel.getProductDetails(productId, this)

    }

    override fun displayProductData(product: Product) {
        productDetailsBinding.product = product
        productDetailsBinding.addToCart.setOnClickListener(
                {
                    val cart = Cart(product.name, product.price, product.category, product.image, 1, product.id)
                    addProductToCart(cart)
                }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.cart_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.cart -> {
                startActivity(Intent(this, CartActivity::class.java))
            }
            android.R.id.home -> {
                finish()
            }
        }
        return false
    }

    private fun addProductToCart(cart: Cart) {

        doAsync {
            val cartDao = RetailStoreDatabase.getInstance(application).cartDao()
            cartDao.insertProduct(cart)
        }
        Toast.makeText(this, "Product added successfully to cart", Toast.LENGTH_LONG).show()

    }


}
