package com.retailStore.productList.data

import com.retailStore.productList.data.source.ProductListDataSource

class ProductListRepository(private val productListDataSource: ProductListDataSource) {

    fun getProductList(category: String, callBack: ProductListDataSource.CallBack) {
        productListDataSource.getProductDetails(category, callBack)
    }

}