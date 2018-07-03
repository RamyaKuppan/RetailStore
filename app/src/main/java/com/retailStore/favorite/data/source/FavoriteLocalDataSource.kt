package com.retailStore.favorite.data.source

import com.retailStore.cart.data.Cart
import com.retailStore.favorite.data.FavoriteItem
import org.jetbrains.anko.doAsync

class FavoriteLocalDataSource(var favoriteDao: FavoriteDao) : FavoriteDataSoucre {

    override fun getFavoriteList(callback: FavoriteDataSoucre.CallBack) {
        doAsync {

            val favoriteItem = favoriteDao.getFavoriteList() as ArrayList<FavoriteItem>
            callback.onSuccess( favoriteItem)
        }
    }
}