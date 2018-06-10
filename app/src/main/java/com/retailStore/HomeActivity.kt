package com.retailStore

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.retailStore.cart.CartActivity
import com.retailStore.category.CategoryListAdapter
import com.retailStore.category.CategoryListListener
import com.retailStore.category.CategoryViewModel
import com.retailStore.category.data.CategoryLocalSource
import com.retailStore.category.data.CategoryRepo
import com.retailStore.database.RetailStoreDatabase
import com.retailStore.databinding.ActivityHomeBinding
import com.retailStore.productList.ProductListFragment

class HomeActivity : BaseActivity(), CategoryListListener {

    private lateinit var activityHomeBinding: ActivityHomeBinding
    private lateinit var categoryListAdapter: CategoryListAdapter
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_home)

        categoryListAdapter = CategoryListAdapter(this)
        activityHomeBinding.categoryList.adapter = categoryListAdapter
        setViewModel()
    }

    private fun setViewModel() {
        val productDao = RetailStoreDatabase.getInstance(application).productDao()

        val categoryFactory = CategoryViewModel.Factory(application, CategoryRepo(CategoryLocalSource(productDao)))

        categoryViewModel = ViewModelProviders.of(this, categoryFactory)
                .get(CategoryViewModel::class.java)

        categoryViewModel.getCategoryList(this)
    }

    override fun onCategoryLoad(list: ArrayList<String>) {
        categoryListAdapter.setCategoryList(list)
    }

    override fun onCategoryClick(category: String) {
        val intent = Intent(this, ProductActivity::class.java)
        intent.putExtra(ProductListFragment.CATEGORY, category)
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
        }
        return false
    }

    fun showToast(view: View)
    {
        Toast.makeText(this,"Work In Progress",Toast.LENGTH_LONG).show()
    }
}
