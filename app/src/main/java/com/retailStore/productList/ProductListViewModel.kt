package com.retailStore.productList

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.retailStore.productList.data.Product
import com.retailStore.productList.data.ProductListRepository
import com.retailStore.productList.data.source.ProductListDataSource
import java.util.*

class ProductListViewModel(application: Application, private var respository: ProductListRepository) : AndroidViewModel(application), ProductListDataSource.CallBack {

    private lateinit var productListListener: ProductListListener

    /**
     * Get the product list from the repository
     */
    fun getProductList(category: String, listener: ProductListListener) {
        productListListener = listener
        respository.getProductList(category, this)
    }

    override fun onFailure(message: String) {
        Log.e("ProductListViewModel: ", message)
    }

    override fun onSuccess(productList: ArrayList<Product>) {
        productListListener.onProductLoad(productList)
    }

    class Factory(private val application: Application, private val listRepo: ProductListRepository) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Application::class.java, ProductListRepository::class.java).newInstance(application, listRepo)
        }
    }
}