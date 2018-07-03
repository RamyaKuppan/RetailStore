package com.retailStore.favorite.data.source

class FavoriteRepository(private var dataSoucre: FavoriteDataSoucre) {
    fun getFavoriteList(callBack: FavoriteDataSoucre.CallBack) {
        dataSoucre.getFavoriteList(callBack)
    }

}