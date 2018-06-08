package com.retailStore.productList

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.retailStore.R
import com.retailStore.database.RetailStoreDatabase
import com.retailStore.databinding.ActivityProductListBinding
import com.retailStore.productList.data.Product
import com.retailStore.productList.data.ProductListRepository
import com.retailStore.productList.data.source.ProductListLocalSource
import org.jetbrains.anko.doAsync

class ProductListActivity : AppCompatActivity() {

    private lateinit var productListBinding: ActivityProductListBinding
    private lateinit var productListViewModel: ProductListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initializeData()
    }

    /**
     * Initialize the binding object and assign the respective ViewModel for view
     */
    private fun initBinding() {
        productListBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_product_list)

        val productDao = RetailStoreDatabase.getInstance(application).productDao()

        val productListFactory = ProductListViewModel.Factory(application, ProductListRepository(ProductListLocalSource(productDao)))

        productListViewModel = ViewModelProviders.of(this, productListFactory)
                .get(ProductListViewModel::class.java)


        productListBinding.productListViewModel = productListViewModel
    }


    private fun initializeData() {
        var categoryList: ArrayList<String>
        var product: ArrayList<Product>

        val listOfProduct = HashMap<String, ArrayList<Product>>()

        doAsync {
            val productDao = RetailStoreDatabase.getInstance(application).productDao()

            categoryList = productDao.getProductCategory() as ArrayList<String>

            for (category in categoryList) {
                product = productDao.getProductList(category) as ArrayList<Product>
                listOfProduct[category] = product
            }
        }
    }
}
