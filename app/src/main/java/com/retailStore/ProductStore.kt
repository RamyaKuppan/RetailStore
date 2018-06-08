package com.retailStore

import android.content.Context
import android.content.SharedPreferences
import org.jetbrains.anko.defaultSharedPreferences

object ProductStore {
    const val IS_FIRST_LAUNCH: String = "isFirstLaunch"

    lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.defaultSharedPreferences
    }

    fun storeIsFristLaunch(isFirst: Boolean) {
        sharedPreferences.edit().putBoolean(IS_FIRST_LAUNCH, isFirst).apply()
    }

    fun getIsFristLaunch(): Boolean {
        return sharedPreferences.getBoolean(IS_FIRST_LAUNCH, false)
    }
}