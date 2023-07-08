package com.example.latihansubmisionfaa1.model.local.database.favorite

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Insert
    suspend fun insert(favorite: Favorite)

    @Delete
    suspend fun delete(favorite: Favorite)

    @Query("SELECT * FROM favorites ORDER BY user ASC")
    fun getAllFavorites(): LiveData<List<Favorite>>

    @Query("SELECT * FROM favorites WHERE user = :user")
    suspend fun getFavoriteUserByUser(user: String): Favorite?
}