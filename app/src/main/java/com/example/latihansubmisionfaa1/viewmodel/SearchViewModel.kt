package com.example.latihansubmisionfaa1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.latihansubmisionfaa1.model.remote.response.SearchUserGithubResponse
import com.example.latihansubmisionfaa1.repository.GithubRepository
import com.example.latihansubmisionfaa1.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor (private val repository: GithubRepository) : ViewModel() {

    private var searchUserGithubResponse: SearchUserGithubResponse? = null

    private var _searchResponse = MutableLiveData<RequestState<SearchUserGithubResponse?>>()
    var searchResponse: LiveData<RequestState<SearchUserGithubResponse?>> = _searchResponse

    fun searchUser(query: String) {
        viewModelScope.launch {
            _searchResponse.postValue(RequestState.Loading)
            val response = repository.searchUser(query)
            _searchResponse.postValue(handleSearchUserResponse(response))
        }
    }

    private fun handleSearchUserResponse(response: Response<SearchUserGithubResponse>):
            RequestState<SearchUserGithubResponse?> {
        return if (response.isSuccessful) {
            response.body()?.let {
                searchUserGithubResponse = it
            }
            RequestState.Success(searchUserGithubResponse ?: response.body())
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