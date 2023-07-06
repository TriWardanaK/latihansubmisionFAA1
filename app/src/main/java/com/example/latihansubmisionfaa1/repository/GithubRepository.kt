package com.example.latihansubmisionfaa1.repository

import com.example.latihansubmisionfaa1.model.remote.ApiService
import com.example.latihansubmisionfaa1.model.remote.response.FollowersGithubResponse
import retrofit2.Response
import javax.inject.Inject

class GithubRepository @Inject constructor (private val apiService: ApiService) {

    suspend fun searchUser(query: String) = apiService.getSearchUser(query)

    suspend fun followersUser(username: String): Response<ArrayList<FollowersGithubResponse>> =
        apiService.getFollowers(username)
}