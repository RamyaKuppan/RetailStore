package com.retailStore.sales

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface SalesDao {

    @Insert
    fun insertProducts(cartItems: ArrayList<Sales>)

    @Insert
    fun insertProduct(cartItem: Sales)

    @Delete
    fun deleteProduct(cartItem: Sales)

    @Query("Select * from sale_table")
    fun getCartItems(): List<Sales>

}
