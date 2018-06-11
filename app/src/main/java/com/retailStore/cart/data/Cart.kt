package com.retailStore.cart.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "cart_table")
class Cart(@ColumnInfo(name = "name")
           var name: String = "",

           @ColumnInfo(name = "price")
           var price: Float = 0.0f,

           @ColumnInfo(name = "category")
           var category: String = "",

           @ColumnInfo(name = "image")
           var image: Int = 0,

           @ColumnInfo(name = "count")
           var count: Int = 0,

           @ColumnInfo(name = "productId")
           var productId: Int = 0,

           @PrimaryKey(autoGenerate = true)
           @ColumnInfo(name = "cartId")
           var id: Int = 0)
