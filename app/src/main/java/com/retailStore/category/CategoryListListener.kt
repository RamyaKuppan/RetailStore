package com.retailStore.category

/**
 * Used to load the Category and perform click for particular category
 */
interface CategoryListListener {

    /**
     * Load the category from Database
     */
    fun onCategoryLoad(list: ArrayList<String>)

    /**
     * Used to perform click action for category
     * and display product list based on catefory
     */
    fun onCategoryClick(category: String)
}