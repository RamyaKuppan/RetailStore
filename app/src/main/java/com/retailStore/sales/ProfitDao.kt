package com.retailStore.sales

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ProfitDao {

    @Insert
    fun insertProducts(cartItems: ArrayList<Profit>)

    @Insert
    fun insertProduct(cartItem: Profit)

    @Delete
    fun deleteProduct(cartItem: Profit)

    @Query("Select * from profit_table")
    fun getCartItems(): List<Profit>

}
