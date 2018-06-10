package com.retailStore.productDetail

import com.retailStore.productList.data.Product

interface ProductDetailsListener {
    fun displayProductData(product: Product)
}