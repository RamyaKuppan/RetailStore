package com.retailStore.productList.data

import com.retailStore.productList.data.source.ProductListDataSource

class ProductListRepository(private val productListDataSource: ProductListDataSource) {

    /**
     * Call the [ProductListDataSource.getProductList] to get product list
     * @param category selected category to display product list
     * @param callBack used to pass the product list to [ProductListViewModel]
     */
    fun getProductList(category: String, callBack: ProductListDataSource.CallBack) {
        productListDataSource.getProductList(category, callBack)
    }

}