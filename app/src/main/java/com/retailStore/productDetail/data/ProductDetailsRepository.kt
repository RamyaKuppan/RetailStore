package com.retailStore.productDetail.data

class ProductDetailsRepository(var productDetailsDataSource: ProductDetailsDataSource) {

    fun getProduct(productId: Int, callBack: ProductDetailsDataSource.CallBack) {
        productDetailsDataSource.getProductList(productId, callBack)
    }
}