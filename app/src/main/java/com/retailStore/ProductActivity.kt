package com.retailStore

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.retailStore.cart.CartActivity
import com.retailStore.databinding.ActivityProductListBinding
import com.retailStore.productDetail.ProductDetailActivity
import com.retailStore.productList.ProductListFragment

class ProductActivity : BaseActivity() {

    private lateinit var productListBinding: ActivityProductListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val category = intent.getStringExtra(ProductListFragment.CATEGORY) as String
        title = category
        initBinding(category)
        showBackButton(true)
    }

    /**
     * Initialize the binding object and add [ProductListFragment] fragment
     */
    private fun initBinding(category: String) {
        productListBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_product_list)
        addFragment(ProductListFragment.newInstance(category))
    }

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
        val intent = Intent(this, ProductDetailActivity::class.java)
        val bundle = Bundle()
        bundle.putInt(ProductDetailActivity.PRODUCT_ID, productId)
        intent.putExtras(bundle)
        startActivity(intent)
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
            android.R.id.home -> {
                finish()
            }
        }
        return false
    }

}
