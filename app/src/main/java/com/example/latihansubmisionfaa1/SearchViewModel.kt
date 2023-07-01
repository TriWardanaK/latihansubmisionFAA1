package com.example.latihansubmisionfaa1

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latihansubmisionfaa1.api.RetrofitClient
import com.example.latihansubmisionfaa1.response.SearchUserGithubResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    var liveDataSearch: MutableLiveData<ArrayList<SearchUserGithubResponse.SearchUserResponse>?> =
        MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<ArrayList<SearchUserGithubResponse.SearchUserResponse>?> {
        return liveDataSearch
    }

    val isLoading = MutableLiveData<Boolean>()

    val isEmpty = MutableLiveData<Boolean>()

    fun showSearch(searchActivity: SearchActivity) {
        isLoading.value = true
        searchActivity.getName()?.let {
            RetrofitClient.instance.getSearchUser(it).enqueue(object :
                Callback<SearchUserGithubResponse> {
                override fun onFailure(call: Call<SearchUserGithubResponse>, t: Throwable) {
                    isLoading.value = false
                    Log.d("cekfailure", "${t.message}")
                }

                override fun onResponse(
                    call: Call<SearchUserGithubResponse>,
                    response: Response<SearchUserGithubResponse>
                ) {
                    isLoading.value = false
                    when (response.body()?.results?.size) {
                        0 -> isEmpty.value = true
                        else -> isEmpty.value = false
                    }
                    liveDataSearch.postValue(response.body()?.results)
                }
            })
        }
    }
}

