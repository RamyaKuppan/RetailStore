package com.retailStore.productDetail

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.retailStore.R
import com.retailStore.cart.data.Cart
import com.retailStore.database.RetailStoreDatabase
import com.retailStore.databinding.FragmentProductDetailsBinding
import com.retailStore.productDetail.data.ProductDetailsLocalSource
import com.retailStore.productDetail.data.ProductDetailsRepository
import com.retailStore.productList.data.Product
import org.jetbrains.anko.doAsync

class ProductDetailFragment : Fragment(), ProductDetailsListener {
    private val PRODUCT_ID = "Product_ID"
    private lateinit var productDetailsBinding: FragmentProductDetailsBinding

    companion object {
        private val PRODUCT_ID = "Product_ID"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ProductListFragment.
         */
        fun newInstance(productId: Int): ProductDetailFragment {
            val fragment = ProductDetailFragment()
            val args = Bundle()
            args.putInt(PRODUCT_ID, productId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        productDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_details, container, false)
        return productDetailsBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val productDao = RetailStoreDatabase.getInstance(activity!!.application).productDao()

        val detailsFactory = ProductDetailViewModel.Factory(activity!!.application, ProductDetailsRepository(ProductDetailsLocalSource(productDao)))

        val detailViewModel = ViewModelProviders.of(this, detailsFactory)
                .get(ProductDetailViewModel::class.java)

        detailViewModel.getProductDetails(arguments!!.getInt(PRODUCT_ID), this)
    }

    override fun displayProductData(product: Product) {
        productDetailsBinding.product = product
        productDetailsBinding.addToCart.setOnClickListener(
                {
                    val cart = Cart(product.name, product.price, product.category, product.image, 1)
                    addProductToCart(cart)
                }
        )
    }

    private fun addProductToCart(cart: Cart) {

        doAsync {
            val cartDao = RetailStoreDatabase.getInstance(activity!!.application).cartDao()
            cartDao.insertProduct(cart)
        }
        Toast.makeText(activity, "Product added successfully to cart", Toast.LENGTH_LONG).show()

    }


}