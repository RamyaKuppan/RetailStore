package com.retailStore

import android.app.Application

class RetailStoreApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ProductStore.init(applicationContext)
    }
}