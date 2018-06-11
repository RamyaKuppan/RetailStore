package com.retailStore.productList.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.retailStore.R

@Entity(tableName = "product_table")
class Product(@ColumnInfo(name = "type")
              var type: String = "",

              @ColumnInfo(name = "name")
              var name: String = "",

              @ColumnInfo(name = "price")
              var price: Float = 0.0f,

              @ColumnInfo(name = "category")
              var category: String = "",

              @ColumnInfo(name = "image")
              var image: Int = R.drawable.electronics,

              @PrimaryKey(autoGenerate = true)
              @ColumnInfo(name = "id")
              var id: Int = 0)

