package com.example.latihansubmisionfaa1.api

import com.example.latihansubmisionfaa1.response.FollowersGithubResponse
import com.example.latihansubmisionfaa1.response.SearchUserGithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    fun getSearchUser(@Query("q")text: String): Call<SearchUserGithubResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username")text: String): Call<ArrayList<FollowersGithubResponse>>
}