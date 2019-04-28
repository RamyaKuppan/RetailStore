package com.retailStore.sales

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "profit_table")
class Profit(@ColumnInfo(name = "name")
             var name: String? = "",
             @PrimaryKey(autoGenerate = true)
             @ColumnInfo(name = "id")
             var id: Int = 0)
