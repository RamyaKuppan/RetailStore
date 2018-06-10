package com.retailStore.productDetail.data

import com.retailStore.productList.data.Product

interface ProductDetailsDataSource {
    interface CallBack {
        fun onSuccess(product: Product)

        fun onFailure(message: String)
    }

    fun getProductList(productId: Int, callBack: CallBack)
}