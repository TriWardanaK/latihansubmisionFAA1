package com.example.latihansubmisionfaa1

import android.util.Log
import androidx.lifecycle.LiveData
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

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    fun showSearch(searchActivity: SearchActivity) {
        _isLoading.value = true
        searchActivity.getName()?.let {
            RetrofitClient.instance.getSearchUser(it).enqueue(object :
                Callback<SearchUserGithubResponse> {
                override fun onFailure(call: Call<SearchUserGithubResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.d("cekfailure", "${t.message}")
                }

                override fun onResponse(
                    call: Call<SearchUserGithubResponse>,
                    response: Response<SearchUserGithubResponse>
                ) {
                    _isLoading.value = false
                    when (response.body()?.results?.size) {
                        0 -> _isEmpty.value = true
                        else -> _isEmpty.value = false
                    }
                    liveDataSearch.postValue(response.body()?.results)
                }
            })
        }
    }
}

