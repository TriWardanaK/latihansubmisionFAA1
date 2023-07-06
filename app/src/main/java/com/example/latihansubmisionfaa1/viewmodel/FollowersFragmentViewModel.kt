package com.example.latihansubmisionfaa1.viewmodel

import androidx.lifecycle.*
import com.example.latihansubmisionfaa1.model.remote.response.FollowersGithubResponse
import com.example.latihansubmisionfaa1.repository.GithubRepository
import com.example.latihansubmisionfaa1.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FollowersFragmentViewModel @Inject constructor (private val repository: GithubRepository) : ViewModel() {

    private var followersGithubResponse: ArrayList<FollowersGithubResponse>? = null

    private var _followersResponse =
        MutableLiveData<RequestState<ArrayList<FollowersGithubResponse>?>>()
    var followersResponse: LiveData<RequestState<ArrayList<FollowersGithubResponse>?>> =
        _followersResponse

    fun getFollowersUser(user: String) {
        viewModelScope.launch {
            _followersResponse.postValue(RequestState.Loading)
            val response = repository.followersUser(user)
            _followersResponse.postValue(handleFollowersUserResponse(response))
        }
    }


    private fun handleFollowersUserResponse(response: Response<ArrayList<FollowersGithubResponse>>):
            RequestState<ArrayList<FollowersGithubResponse>?> {
        return if (response.isSuccessful) {
            response.body()?.let {
                followersGithubResponse = it
            }
            RequestState.Success(followersGithubResponse ?: response.body())
        } else RequestState.Error(
            try {
                response.errorBody()?.string()?.let {
                    JSONObject(it).get("status_message")
                }
            } catch (e: JSONException) {
                e.localizedMessage
            } as String
        )
    }
}
