package com.example.latihansubmisionfaa1.repository

import androidx.lifecycle.LiveData
import com.example.latihansubmisionfaa1.model.local.database.favorite.Favorite
import com.example.latihansubmisionfaa1.model.local.database.favorite.FavoriteDao
import javax.inject.Inject

class FavoriteRepository @Inject constructor (private val favoriteDao: FavoriteDao) {

    val getAllFavorites: LiveData<List<Favorite>> = favoriteDao.getAllFavorites()

    suspend fun insert(favorite: Favorite) {
        favoriteDao.insert(favorite)
    }

    suspend fun delete(favorite: Favorite) {
        favoriteDao.delete(favorite)
    }

    suspend fun getFavoriteUserByUser(user: String): Favorite? {
        return favoriteDao.getFavoriteUserByUser(user)
    }
}