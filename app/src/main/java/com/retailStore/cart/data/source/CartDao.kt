package com.retailStore.cart.data.source

import android.arch.persistence.room.*
import com.retailStore.cart.data.Cart
import com.retailStore.productList.data.Product

/**
 * CartDao is used to store and get cart items
 */
@Dao
interface CartDao {

    @Insert
    fun insertProducts(products: ArrayList<Cart>)

    @Insert
    fun insertProduct(product: Cart)

    @Delete
    fun deleteProduct(product: Product)

    @Query("Select * from cart_table")
    fun getCartItems(): List<Cart>

    @Update
    fun updateProducts(products: ArrayList<Cart>)
}
