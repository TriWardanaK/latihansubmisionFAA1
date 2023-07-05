package com.example.latihansubmisionfaa1.repository

import com.example.latihansubmisionfaa1.model.remote.network.RetrofitClient
import com.example.latihansubmisionfaa1.model.remote.response.FollowersGithubResponse
import retrofit2.Response

class GithubRepository {
    private val client = RetrofitClient.instance

    suspend fun searchUser(query: String) = client.getSearchUser(query)

    suspend fun followersUser(username: String): Response<ArrayList<FollowersGithubResponse>> =
        client.getFollowers(username)
}