package com.retailStore.productList

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.retailStore.R
import com.retailStore.databinding.ItemProductBinding
import com.retailStore.productList.data.Product

/**
 * Display the list of product based on category
 */
class ProductListAdapter(private val productListListener: ProductListListener) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var products: ArrayList<Product> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemProductBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.context), R.layout.item_product,
                        parent, false)
        return ProductViewHolder(binding, productListListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductViewHolder).onBind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun setProducts(categoryList: ArrayList<Product>) {
        products = categoryList
        notifyDataSetChanged()
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        (holder as? ProductViewHolder)?.unBind()
    }

    class ProductViewHolder(private val binding: ItemProductBinding, var productListListener: ProductListListener) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(product: Product) {
            binding.product = product
            binding.productLayout.setOnClickListener(
                    {
                        productListListener.onProductClick(product.id)
                    }
            )
            binding.executePendingBindings()

        }

        fun unBind() {
            binding.unbind()
        }
    }
}