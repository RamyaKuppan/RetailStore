package com.retailStore.cart.data.source

import android.arch.persistence.room.*
import com.retailStore.cart.data.Cart

/**
 * CartDao is used to store and get cart items
 */
@Dao
interface CartDao {

    @Insert
    fun insertProducts(cartItems: ArrayList<Cart>)

    @Insert
    fun insertProduct(cartItem: Cart)

    @Delete
    fun deleteProduct(cartItem: Cart)

    @Query("Select * from cart_table")
    fun getCartItems(): List<Cart>

    @Query("Delete from cart_table where cartId=:id")
    fun deleteCart(id: Int)

    @Update
    fun updateProducts(products: ArrayList<Cart>)
}
