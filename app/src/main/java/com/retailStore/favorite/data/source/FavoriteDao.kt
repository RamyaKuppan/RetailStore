package com.retailStore.favorite.data.source

import android.arch.persistence.room.*
import com.retailStore.favorite.data.FavoriteItem

@Dao
interface FavoriteDao {

    @Insert
    fun addFavorite(favoriteItem: FavoriteItem)

    @Query("Select * from favorite_table")
    fun getFavoriteList(): List<FavoriteItem>
}
