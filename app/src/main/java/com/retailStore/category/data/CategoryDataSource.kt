package com.retailStore.category.data

interface CategoryDataSource {
    interface CallBack {
        fun onSuccess(categoryList: ArrayList<String>)
        fun onFailure(message: String)
    }

    /**
     * Get the categories from the [RetailStoreDatabase]
     */
    fun getCategories(callBack: CallBack)
}