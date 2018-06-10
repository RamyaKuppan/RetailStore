package com.retailStore.productList.data.source

import com.retailStore.productList.data.Product
import org.jetbrains.anko.doAsync

class ProductListLocalSource(var productDao: ProductDao) : ProductListDataSource {

    override fun getProductList(category: String, callBack: ProductListDataSource.CallBack) {
        var product: ArrayList<Product>


        doAsync {
            product = productDao.getProductList(category) as ArrayList<Product>
            callBack.onSuccess(product)
        }
    }


}