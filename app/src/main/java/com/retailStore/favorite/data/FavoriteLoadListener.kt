package com.retailStore.favorite.data

interface FavoriteLoadListener {
    /**
     * Get the list cart item from database
     */
    fun onFavoriteItemLoad(favoriteItem: ArrayList<FavoriteItem>)

}