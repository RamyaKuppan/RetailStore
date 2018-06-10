package com.retailStore

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.retailStore.cart.CartActivity
import com.retailStore.databinding.ActivityProductListBinding
import com.retailStore.productDetail.ProductDetailFragment
import com.retailStore.productList.ProductListFragment

class ProductActivity : AppCompatActivity() {

    private lateinit var productListBinding: ActivityProductListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Store Products"
        initBinding()
//        initializeData()
    }

    /**
     * Initialize the binding object
     */
    private fun initBinding() {
        productListBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_product_list)
        addFragment(ProductListFragment.newInstance())
    }


    /* private fun initializeData() {
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
 */
    /**
     * Used to add the fragment that passed in method argument
     * @param fragment Fragment to add in activity
     */
    private fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    /** Shows the product detail fragment  */
    fun show(productId: Int) {

        val productFragment = ProductDetailFragment.newInstance(productId)

        supportFragmentManager
                .beginTransaction()
                .addToBackStack("product")
                .replace(R.id.frame_container,
                        productFragment, null).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.cart_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.cart -> {
                startActivity(Intent(this, CartActivity::class.java))
            }
        }
        return false
    }
}
