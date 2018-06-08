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
        val productList = addProducts()

        doAsync {
            val productDao = RetailStoreDatabase.getInstance(application).productDao()
            for (product in productList) {
                productDao.insert(product)
            }
        }

        startProductListActivity()
    }

    /**
     * Start the product list activity to display list of products
     */
    private fun startProductListActivity() {
        Handler().postDelayed({
            startActivity(Intent(this, Product::class.java))
            finish()
        }, splashScreenDuration.toLong())
    }


    /**
     * Initially get the product details
     */
    private fun addProducts(): ArrayList<Product> {
        val productList = ArrayList<Product>()

        productList.add(Product("Microwave oven", 10000.0f, "Electronics", ""))
        productList.add(Product("Television", 25000.0f, "Electronics", ""))
        productList.add(Product("Vacuum Cleaner", 20000.0f, "Electronics", ""))
        productList.add(Product("Table", 10.0f, "Furniture", ""))
        productList.add(Product("Chair", 20.0f, "Furniture", ""))
        productList.add(Product("Almirah", 20.0f, "Furniture", ""))

        return productList
    }
}
