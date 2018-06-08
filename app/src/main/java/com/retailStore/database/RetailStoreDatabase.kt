package com.retailStore.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.retailStore.productList.data.Product
import com.retailStore.productList.data.source.ProductDao

@Database(entities = [(Product::class)], version = 1)
abstract class RetailStoreDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        private var INSTANCE: RetailStoreDatabase? = null

        private const val DB_NAME = "retail_store_db"

        private val lock = Any()

        fun getInstance(context: Context): RetailStoreDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            RetailStoreDatabase::class.java, DB_NAME)
                            .build()
                }
                return INSTANCE!!
            }
        }
    }
}