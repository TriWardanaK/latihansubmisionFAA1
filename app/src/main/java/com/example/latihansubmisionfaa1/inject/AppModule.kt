package com.example.latihansubmisionfaa1.inject

import android.content.Context
import com.example.latihansubmisionfaa1.model.local.database.favorite.FavoriteDao
import com.example.latihansubmisionfaa1.model.local.database.favorite.FavoriteRoomDatabase
import com.example.latihansubmisionfaa1.model.remote.ApiService
import com.example.latihansubmisionfaa1.repository.FavoriteRepository
import com.example.latihansubmisionfaa1.repository.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //local
    @Provides
    @Singleton
    fun provideFavoriteDatabase(@ApplicationContext context: Context): FavoriteRoomDatabase {
        return FavoriteRoomDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(database: FavoriteRoomDatabase): FavoriteDao {
        return database.favoriteDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(favoriteDao: FavoriteDao): FavoriteRepository {
        return FavoriteRepository(favoriteDao)
    }

    //remote
    @Provides
    @Singleton
    fun provideGithubRepository(apiService: ApiService): GithubRepository {
        return GithubRepository(apiService)
    }
}