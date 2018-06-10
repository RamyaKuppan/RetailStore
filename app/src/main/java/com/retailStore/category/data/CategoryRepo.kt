package com.retailStore.category.data


class CategoryRepo(private var dataSource: CategoryDataSource) {

    /**
     * Get the categories from [CategoryDataSource]
     */
    fun getCategories(callBack: CategoryDataSource.CallBack) {
        dataSource.getCategories(callBack)
    }
}