package com.retailStore.productList

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.retailStore.ObservableViewModel
import com.retailStore.productList.data.Product
import com.retailStore.productList.data.ProductListRepository
import com.retailStore.productList.data.source.ProductListDataSource
import java.util.*

class ProductListViewModel(application: Application, var respository: ProductListRepository) : ObservableViewModel(application), ProductListDataSource.CallBack {

    var products = HashMap<String, ArrayList<Product>>()

    fun getProductList() {
        respository.getProductList("", this)
    }

    override fun onFailure(message: String) {
        Log.e("ProductListViewModel: ", message)
    }

    override fun onSuccess(productList: HashMap<String, ArrayList<Product>>) {
        products = productList
    }

    class Factory(private val application: Application, private val listRepo: ProductListRepository) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Application::class.java, ProductListRepository::class.java).newInstance(application, listRepo)
        }
    }
}