package com.retailStore.favorite

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.retailStore.favorite.data.FavoriteItem
import com.retailStore.favorite.data.FavoriteLoadListener
import com.retailStore.favorite.data.source.FavoriteDataSoucre
import com.retailStore.favorite.data.source.FavoriteRepository

class FavoriteViewModel(application: Application, var repository: FavoriteRepository) : AndroidViewModel(application), FavoriteDataSoucre.CallBack {

    lateinit var favoriteListener: FavoriteLoadListener

    fun getFavorite(favoriteLoadListener: FavoriteLoadListener) {
        this.favoriteListener = favoriteLoadListener
        repository.getFavoriteList(this)
    }

    class Factory(private val application: Application, private val favoriteRepository: FavoriteRepository) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Application::class.java, FavoriteRepository::class.java).newInstance(application, favoriteRepository)
        }
    }

    override fun onFailure(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess(favoriteList: ArrayList<FavoriteItem>) {
        favoriteListener.onFavoriteItemLoad(favoriteList)

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
