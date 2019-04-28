package com.retailStore.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.content.Context
import com.retailStore.cart.data.Cart
import com.retailStore.cart.data.source.CartDao
import com.retailStore.productList.data.Product
import com.retailStore.productList.data.source.ProductDao
import com.retailStore.sales.Sales
import com.retailStore.sales.SalesDao

@Database(entities = [(Product::class), (Cart::class), Sales::class], version = 2)
abstract class RetailStoreDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
    abstract fun saleDao(): SalesDao


    companion object {
        private var INSTANCE: RetailStoreDatabase? = null

        private const val DB_NAME = "retail_store_db"

        private val lock = Any()

        fun getInstance(context: Context): RetailStoreDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            RetailStoreDatabase::class.java, DB_NAME)
                            .addMigrations(migration)
                            .build()
                }
                return INSTANCE!!
            }
        }

        val migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE `sale_table` (`id` INTEGER Not Null, `name` TEXT, " +
                        "PRIMARY KEY(`id`))")
            }
        }

    }
}