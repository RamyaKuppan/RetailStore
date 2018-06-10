package com.retailStore

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.retailStore.database.RetailStoreDatabase
import com.retailStore.productList.data.Product
import org.jetbrains.anko.doAsync

class SplashActivity : AppCompatActivity() {

    private val splashScreenDuration = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val isFirstLaunch = ProductStore.getIsFristLaunch()

        if (!isFirstLaunch) {
            val productList = addProducts()
            doAsync {
                val productDao = RetailStoreDatabase.getInstance(application).productDao()
                productDao.insertAll(productList)
            }

            ProductStore.storeIsFirstLaunch(true)

        }

        startProductListActivity()
    }

    /**
     * Start the product list activity to display list of products
     */
    private fun startProductListActivity() {
        Handler().postDelayed({
            startActivity(Intent(this, ProductActivity::class.java))
            finish()
        }, splashScreenDuration.toLong())
    }


    /**
     * Initially get the product details
     */
    private fun addProducts(): ArrayList<Product> {
        val productList = ArrayList<Product>()

        productList.add(Product("Microwave oven", "Samsung 23 L Solo Microwave Oven",
                6250.0f, "Electronics", ""))
        productList.add(Product("Microwave oven", "Morphy Richards 23 L Convection Microwave Oven (23MCG, Black)",
                9084.0f, "Electronics", ""))

        productList.add(Product("Television", "Panasonic 55cm (22 inch) Full HD LED TV",
                9000.0f, "Electronics", ""))
        productList.add(Product("Television", "Sony 80cm (32 inch) HD Ready LED TV",
                19999.0f, "Electronics", ""))

        productList.add(Product("Vacuum Cleaner", "Eureka Forbes Rapid Handheld Vacuum Cleaner",
                2062.0f, "Electronics", ""))
        productList.add(Product("Vacuum Cleaner", "Petrice Multi-Functional Vacuum Cleaner",
                2199.0f, "Electronics", ""))

        productList.add(Product("Table", "Claire Coffee Table",
                8499.0f, "Furniture", ""))
        productList.add(Product("Table", "Terry Study Table",
                8999.0f, "Furniture", ""))

        productList.add(Product("Chair", "Milano Cushioned Metallic Chair",
                1526.0f, "Furniture", ""))
        productList.add(Product("Chair", "Ergonomic Chair ",
                3999.0f, "Furniture", ""))

        productList.add(Product("Almirah", "Kairy Two Door Shoe Cabinet",
                3599.0f, "Furniture", ""))
        productList.add(Product("Almirah", "Kairy Two Door Shoe Cabinet",
                9499.0f, "Furniture", ""))

        return productList
    }
}
