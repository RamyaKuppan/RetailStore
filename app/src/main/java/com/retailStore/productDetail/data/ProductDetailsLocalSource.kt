package com.retailStore.productDetail.data

import com.retailStore.productList.data.Product
import com.retailStore.productList.data.source.ProductDao
import org.jetbrains.anko.doAsync

class ProductDetailsLocalSource(var productDao: ProductDao) : ProductDetailsDataSource {

    override fun getProductList(productId: Int, callBack: ProductDetailsDataSource.CallBack) {
        var product: Product

        doAsync {
            product = productDao.getProductUsingID(productId) as Product
            callBack.onSuccess(product)
        }
    }
}