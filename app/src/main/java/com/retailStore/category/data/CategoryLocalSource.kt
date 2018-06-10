package com.retailStore.category.data

import com.retailStore.productList.data.source.ProductDao
import org.jetbrains.anko.doAsync

class CategoryLocalSource(private var productDao: ProductDao) : CategoryDataSource {

    override fun getCategories(callBack: CategoryDataSource.CallBack) {

        doAsync {

            val categoryList = productDao.getProductCategory() as ArrayList<String>
            callBack.onSuccess(categoryList)
        }
    }

}