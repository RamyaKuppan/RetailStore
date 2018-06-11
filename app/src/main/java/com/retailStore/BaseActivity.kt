package com.retailStore

import android.support.v7.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    /**
     * Enable the back arrow button for the screen
     * @param show to display the back arrow if true or false
     */
    fun showBackButton(show: Boolean) {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(show)
            it.setDisplayShowHomeEnabled(show)
        }
    }

}