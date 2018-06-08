package com.retailStore.productList.data.source

import com.retailStore.productList.data.Product
import org.jetbrains.anko.doAsync

class ProductListLocalSource(var productDao: ProductDao) : ProductListDataSource {

    override fun getProductDetails(category: String, callBack: ProductListDataSource.CallBack) {
        var categoryList: ArrayList<String>
        var product: ArrayList<Product>

        val listOfProduct = HashMap<String, ArrayList<Product>>()

        doAsync {
            categoryList = productDao.getProductCategory() as ArrayList<String>

            categoryList.forEach { category ->
                product = productDao.getProductList(category) as ArrayList<Product>
                listOfProduct[category] = product
            }

            callBack.onSuccess(listOfProduct)
        }
    }


}