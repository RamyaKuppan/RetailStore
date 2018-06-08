package com.retailStore.productList.data.source

import android.arch.persistence.room.*
import com.retailStore.productList.data.Product

@Dao
interface ProductDao {
    @Insert
    fun insert(product: Product)

    @Insert
    fun insertAll(products: ArrayList<Product>)

    @Query("Select * from product_table where category=:selectedCategory")
    fun getProductList(selectedCategory: String): List<Product>

    @Query("Select distinct category from product_table")
    fun getProductCategory(): List<String>

    @Delete
    fun delete(product: Product)

    @Query("Delete From product_table")
    fun deleteAll()

    @Query("Select *from product_table where id =:id")
    fun getProductUsingID(id: Int): Product

    @Update
    fun updateProduct(products: ArrayList<Product>)

}