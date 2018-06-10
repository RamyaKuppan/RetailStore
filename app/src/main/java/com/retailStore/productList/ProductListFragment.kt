package com.retailStore.productList

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.retailStore.ProductActivity
import com.retailStore.R
import com.retailStore.category.CategoryListAdapter
import com.retailStore.category.CategoryListListener
import com.retailStore.category.CategoryViewModel
import com.retailStore.category.data.CategoryLocalSource
import com.retailStore.category.data.CategoryRepo
import com.retailStore.database.RetailStoreDatabase
import com.retailStore.databinding.FragmentProductListBinding
import com.retailStore.productList.data.Product
import com.retailStore.productList.data.ProductListRepository
import com.retailStore.productList.data.source.ProductDao
import com.retailStore.productList.data.source.ProductListLocalSource

/**
 * A simple [Fragment] subclass.
 * Used to display the list of Product based on category
 * Use the [ProductListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductListFragment : Fragment(), CategoryListListener, ProductListListener {

    lateinit var categoryViewModel: CategoryViewModel
    lateinit var productListBinding: FragmentProductListBinding
    lateinit var categoryListAdapter: CategoryListAdapter
    lateinit var productListAdapter: ProductListAdapter
    lateinit var productDao: ProductDao

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ProductListFragment.
         */
        fun newInstance(): ProductListFragment {
            return ProductListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        productListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)
        productDao = RetailStoreDatabase.getInstance(activity!!.application).productDao()

        categoryListAdapter = CategoryListAdapter(this)
        productListBinding.categoryList.adapter = categoryListAdapter

        productListAdapter = ProductListAdapter(this)
        productListBinding.productList.adapter = productListAdapter

        return productListBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val categotyFactory = CategoryViewModel.Factory(activity!!.application, CategoryRepo(CategoryLocalSource(productDao)))

        categoryViewModel = ViewModelProviders.of(this, categotyFactory)
                .get(CategoryViewModel::class.java)

        categoryViewModel.getCategoryList(this)
    }

    override fun onCategoryLoad(list: ArrayList<String>) {
        if (list.size == 0) {
            productListBinding.isCategory = false
        } else {
            productListBinding.isCategory = true
            categoryListAdapter.setCategoryList(list)
            initializeProductList(list[0])
        }
    }

    override fun onCategoryClick(category: String) {
        initializeProductList(category)
    }

    override fun onProductLoad(products: ArrayList<Product>) {
        if (products.size == 0) {
            productListBinding.isCategory = false
        } else {
            productListBinding.isCategory = true
            productListAdapter.setProducts(products)
        }
    }

    override fun onProductClick(productID: Int) {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            (activity as ProductActivity).show(productID)
        }
    }

    private fun initializeProductList(category: String) {
        val productListFactory = ProductListViewModel.Factory(activity!!.application, ProductListRepository(ProductListLocalSource(productDao)))

        val productListViewModel = ViewModelProviders.of(this, productListFactory)
                .get(ProductListViewModel::class.java)
        productListViewModel.getProductList(category, this)
    }
}