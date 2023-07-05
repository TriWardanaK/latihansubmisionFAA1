package com.example.latihansubmisionfaa1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.latihansubmisionfaa1.model.remote.response.SearchUserGithubResponse
import com.example.latihansubmisionfaa1.repository.SearchRepository
import com.example.latihansubmisionfaa1.util.RequestState
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

class SearchViewModel : ViewModel() {
    private val searchRepository = SearchRepository()
    private var searchUserGithubResponse: SearchUserGithubResponse? = null

    private var _searchResponse = MutableLiveData<RequestState<SearchUserGithubResponse?>>()
    var searchResponse: LiveData<RequestState<SearchUserGithubResponse?>> = _searchResponse

    fun searchUser(query: String) {
        viewModelScope.launch {
            _searchResponse.postValue(RequestState.Loading)
            val response = searchRepository.searchUser(query)
            _searchResponse.postValue(handlesearchUserResponse(response))
        }
    }

    private fun handlesearchUserResponse(response: Response<SearchUserGithubResponse>):
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

//    var dataSearch = MutableLiveData<String>()
//
//    fun setSearchQuery(query: String) {
//        dataSearch.value = query
//    }

//    var _liveDataSearch: MutableLiveData<ArrayList<SearchUserGithubResponse.SearchUserResponse>?> =
//        MutableLiveData()
//
//    fun getLiveDataObserver(): MutableLiveData<ArrayList<SearchUserGithubResponse.SearchUserResponse>?> {
//        return _liveDataSearch
//    }

//    private val _dataResponse = MutableLiveData<ArrayList<SearchUserGithubResponse.SearchUserResponse>>()
//    val dataResponse: LiveData<ArrayList<SearchUserGithubResponse.SearchUserResponse>> get () = _dataResponse

//    private val _error = MutableLiveData<Throwable>()
//    val error: LiveData<Throwable> get() = _error

//    fun getLiveDataObserver(): MutableLiveData<ArrayList<SearchUserGithubResponse.SearchUserResponse>?> {
//        return liveDataSearch
//    }

//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    private val _isEmpty = MutableLiveData<Boolean>()
//    val isEmpty: LiveData<Boolean> = _isEmpty
//
//    fun loadShowSearch(queryUser: String) {
//        viewModelScope.launch {
//            searchRepository.getSearch(queryUser)
//            }
//        }
//    }
//        viewModelScope.launch {
//            searchRepository.getSearch(queryUser) { dataResponse, error ->
//                when (_liveDataSearch.value?.size) {
//                    0 -> {
//                        _isLoading.value = false
//                        _isEmpty.value = true
//                    }
//                    null -> {
//                        _isLoading.value = false
//                        _isEmpty.value = true
//                        _error.value = error
//                    }
//                    else -> {
//                        _isLoading.value = false
//                        _isEmpty.value = false
//                        _liveDataSearch.value = dataResponse
//                    }
//                }
//            }
//        }
//    }

//    fun loadShowSearch(queryUser: String) {
//        searchRepository.getSearch(queryUser) { liveDataSearch, error ->
//            when (liveDataSearch?.size) {
//                0 -> {
//                    _isLoading.value = false
//                    _isEmpty.value = true
//                }
//                null -> {
//                    _isLoading.value = false
//                    _isEmpty.value = true
//                    _error.value = error
//                }
//                else -> {
//                    _isLoading.value = false
//                    _isEmpty.value = false
//                    _liveDataSearch.value = liveDataSearch
//                }
//            }
//        }
//    }

//    fun showSearch(searchActivity: SearchActivity) {
//        _isLoading.value = true
//        searchActivity.getName().let {
//            RetrofitClient.instance.getSearchUser(it).enqueue(object :
//                Callback<SearchUserGithubResponse> {
//                override fun onFailure(call: Call<SearchUserGithubResponse>, t: Throwable) {
//                    _isLoading.value = false
//                    Log.d("cekfailure", "${t.message}")
//                }
//
//                override fun onResponse(
//                    call: Call<SearchUserGithubResponse>,
//                    response: Response<SearchUserGithubResponse>
//                ) {
//                    _isLoading.value = false
//                    when (response.body()?.results?.size) {
//                        0 -> _isEmpty.value = true
//                        null -> _isEmpty.value = true
//                        else -> _isEmpty.value = false
//                    }
//                    liveDataSearch.postValue(response.body()?.results)
//                }
//            })
//        }
//    }