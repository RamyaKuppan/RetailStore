package com.retailStore.productList

import com.retailStore.productList.data.Product

interface ProductListListener {

    /**
     * Load the product to ProductListFragment from Database
     */
    fun onProductLoad(products: ArrayList<Product>)

    /**
     * Used to perform click action for particular product
     */
    fun onProductClick(productID: Int)
}