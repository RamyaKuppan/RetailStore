package com.retailStore.productDetail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.retailStore.productDetail.data.ProductDetailsDataSource
import com.retailStore.productDetail.data.ProductDetailsRepository
import com.retailStore.productList.data.Product

class ProductDetailViewModel(application: Application, val detailsRepository: ProductDetailsRepository) : AndroidViewModel(application), ProductDetailsDataSource.CallBack {

    private lateinit var productDetailsListener: ProductDetailsListener

    fun getProductDetails(productId: Int, productDetailsListener: ProductDetailsListener) {
        this.productDetailsListener = productDetailsListener
        detailsRepository.getProduct(productId, this)
    }

    override fun onFailure(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess(product: Product) {
        productDetailsListener.displayProductData(product)
    }

    class Factory(private val application: Application, private val detailsRepository: ProductDetailsRepository) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Application::class.java, ProductDetailsRepository::class.java).newInstance(application, detailsRepository)
        }
    }


}