package com.retailStore.productList.data.source

import com.retailStore.productList.data.Product
import java.util.*

interface ProductListDataSource {

    interface CallBack {
        fun onSuccess(productList: ArrayList<Product>)

        fun onFailure(message: String)
    }

    /**
     * Get the product list from [RetailStoreDatabase]
     */
    fun getProductList(category: String, callBack: CallBack)
}