package com.example.latihansubmisionfaa1.repository

import com.example.latihansubmisionfaa1.model.remote.Api
import com.example.latihansubmisionfaa1.model.remote.network.RetrofitClient
import com.example.latihansubmisionfaa1.model.remote.response.SearchUserGithubResponse
import retrofit2.http.Query

class SearchRepository {
    private val client = RetrofitClient.instance

    suspend fun searchUser(query: String) = client.getSearchUser(query)
}

//    private val api: Api = RetrofitClient.instance
//
//    fun getSearch(
//        queryUser: String,
////        callback: (ArrayList<SearchUserGithubResponse.SearchUserResponse>?, Throwable?) -> Unit
//    ) {
//        val response = api.getSearchUser(queryUser)
//        if (response.isSuccessful) {
//            fun asa(){
//
//            }
//            val user = response.body()?.results
//            callback(user, null)
//        } else {
//            val error = Throwable("Failed to fetch user")
//            callback(null, error)
//        }
//    }
//}

//        RetrofitClient.instance.getSearchUser(queryUser).enqueue(object :
//            Callback<SearchUserGithubResponse> {
//            override fun onFailure(call: Call<SearchUserGithubResponse>, t: Throwable) {
//                Log.d("cekfailure","${t.message}")
//            }
//            override fun onResponse(
//                call: Call<SearchUserGithubResponse>,
//                response: Response<SearchUserGithubResponse>
//            ) {
//                if (response.isSuccessful) {
//                    Result.Success(response.body()?.results)
//                } else {
//                   Result.Error()
//                }
//            }
//        })
//    }

