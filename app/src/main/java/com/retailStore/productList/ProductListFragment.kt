package com.retailStore.productList

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.retailStore.R
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
class ProductListFragment : Fragment(), ProductListListener {

    private lateinit var productListBinding: FragmentProductListBinding
    private lateinit var productListAdapter: ProductListAdapter
    private lateinit var productDao: ProductDao

    companion object {

        const val CATEGORY = "Category"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ProductListFragment.
         */
        fun newInstance(category: String): ProductListFragment {

            val fragment = ProductListFragment()
            val args = Bundle()
            args.putString(CATEGORY, category)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        productListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)
        productDao = RetailStoreDatabase.getInstance(activity!!.application).productDao()

        productListAdapter = ProductListAdapter(this)
        productListBinding.productList.adapter = productListAdapter

        return productListBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initializeProductList(arguments!!.getString(CATEGORY))

    }

    override fun onProductLoad(products: ArrayList<Product>) {
        productListAdapter.setProducts(products)
    }

    override fun onProductClick(productID: Int) {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            (activity as ProductActivity).show(productID)
        }
    }

    /**
     * Create a viewModel object using factory pattern
     */
    private fun initializeProductList(category: String) {
        val productListFactory = ProductListViewModel.Factory(activity!!.application, ProductListRepository(ProductListLocalSource(productDao)))

        val productListViewModel = ViewModelProviders.of(this, productListFactory)
                .get(ProductListViewModel::class.java)
        productListViewModel.getProductList(category, this)
    }
}