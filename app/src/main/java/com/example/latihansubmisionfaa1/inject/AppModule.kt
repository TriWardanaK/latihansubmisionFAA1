package com.example.latihansubmisionfaa1.inject

import com.example.latihansubmisionfaa1.model.remote.ApiService
import com.example.latihansubmisionfaa1.repository.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGithubRepository(apiService: ApiService): GithubRepository {
        return GithubRepository(apiService)
    }

}