package com.retailStore.category

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.retailStore.category.data.CategoryDataSource
import com.retailStore.category.data.CategoryRepo

class CategoryViewModel(application: Application, var repository: CategoryRepo) : AndroidViewModel(application), CategoryDataSource.CallBack {

    lateinit var categoryListListener: CategoryListListener

    /**
     * Get the category list
     */
    fun getCategoryList(categoryList: CategoryListListener) {
        categoryListListener = categoryList
        repository.getCategories(this)
    }

    override fun onSuccess(categoryList: ArrayList<String>) {
        categoryListListener.onCategoryLoad(categoryList)
    }

    override fun onFailure(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class Factory(private val application: Application, private val repository: CategoryRepo) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Application::class.java, CategoryRepo::class.java).newInstance(application, repository)
        }
    }
}