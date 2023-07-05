package com.example.latihansubmisionfaa1.model.remote

import com.example.latihansubmisionfaa1.model.remote.response.FollowersGithubResponse
import com.example.latihansubmisionfaa1.model.remote.response.SearchUserGithubResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    suspend fun getSearchUser(@Query("q") text: String): Response<SearchUserGithubResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") text: String): Call<ArrayList<FollowersGithubResponse>>
}