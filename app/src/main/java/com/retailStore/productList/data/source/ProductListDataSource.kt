package com.retailStore.productList.data.source

import com.retailStore.productList.data.Product
import java.util.*

interface ProductListDataSource {

    interface CallBack {
        fun onSuccess(productList: HashMap<String, ArrayList<Product>>)

        fun onFailure(message: String)
    }

    fun getProductDetails(category: String,callBack: CallBack)
}