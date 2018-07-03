package com.retailStore.favorite

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.retailStore.BaseActivity
import com.retailStore.R
import com.retailStore.database.RetailStoreDatabase
import com.retailStore.databinding.ActivityFavoriteBinding
import com.retailStore.favorite.data.FavoriteItem
import com.retailStore.favorite.data.FavoriteListAdapter
import com.retailStore.favorite.data.FavoriteLoadListener
import com.retailStore.favorite.data.source.FavoriteLocalDataSource
import com.retailStore.favorite.data.source.FavoriteRepository

class FavoriteActivity : BaseActivity(), FavoriteLoadListener {

    lateinit var favoriteViewModel: FavoriteViewModel
    lateinit var favoriteListAdapter: FavoriteListAdapter
    lateinit var favoriteBinding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_favorite)
        title = "Favorite List"


        val favoriteDao = RetailStoreDatabase.getInstance(this).favoriteDao()
        val favoriteFactory = FavoriteViewModel.Factory(application, FavoriteRepository(FavoriteLocalDataSource(favoriteDao)))

        favoriteViewModel = ViewModelProviders.of(this, favoriteFactory)
                .get(FavoriteViewModel::class.java)

        favoriteViewModel.getFavorite(this)
        favoriteListAdapter = FavoriteListAdapter()
        favoriteBinding.favoriteItems.adapter = favoriteListAdapter


    }

    override fun onFavoriteItemLoad(favoriteItem: ArrayList<FavoriteItem>) {
        favoriteListAdapter.setCartItems(favoriteItem)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
