package com.retailStore.favorite.data.source

import com.retailStore.cart.data.Cart
import com.retailStore.favorite.data.FavoriteItem

interface FavoriteDataSoucre {

    interface CallBack {
        fun onSuccess(favoriteList: ArrayList<FavoriteItem>)

        fun onFailure( message: String)
    }

    fun getFavoriteList(callBack: CallBack)

}