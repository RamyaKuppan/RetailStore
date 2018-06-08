package com.retailStore.productList.data.source

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.retailStore.productList.data.Product

@Dao
interface ProductDao
{
    @Insert
    fun insert(product: Product)

    @Query("Select * from product_table where category=:selectedCategory")
    fun getProductList(selectedCategory: String):ArrayList<Product>

    @Query("Select distinct category from product_table")
    fun getProductCategory():ArrayList<String>
}