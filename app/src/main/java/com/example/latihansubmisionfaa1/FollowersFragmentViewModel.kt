package com.example.latihansubmisionfaa1

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latihansubmisionfaa1.api.RetrofitClient
import com.example.latihansubmisionfaa1.response.FollowersGithubResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersFragmentViewModel : ViewModel() {

    var liveDataFollowers: MutableLiveData<ArrayList<FollowersGithubResponse>> =
        MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<ArrayList<FollowersGithubResponse>> {
        return liveDataFollowers
    }

    val isLoading = MutableLiveData<Boolean>()

    val isEmpty = MutableLiveData<Boolean>()

    fun showFollowers(followersFragment: FollowersFragment) {
        isLoading.value = true
        followersFragment.getUsername()?.let {
            RetrofitClient.instance.getFollowers(it).enqueue(object :
                Callback<ArrayList<FollowersGithubResponse>> {
                override fun onFailure(call: Call<ArrayList<FollowersGithubResponse>>, t: Throwable) {
                    isLoading.value = false
                    Log.d("cekfailure", "${t.message}")
                }

                override fun onResponse(
                    call: Call<ArrayList<FollowersGithubResponse>>,
                    response: Response<ArrayList<FollowersGithubResponse>>
                ) {
                    isLoading.value = false
                    when (response.body()?.size) {
                        0 -> isEmpty.value = true
                        else -> isEmpty.value = false
                    }
                    liveDataFollowers.postValue(response.body())
                }
            })
        }
    }
}