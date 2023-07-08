package com.example.latihansubmisionfaa1.view.viewmodel.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.latihansubmisionfaa1.model.local.database.favorite.Favorite
import com.example.latihansubmisionfaa1.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor (private val repository: FavoriteRepository) : ViewModel() {

    private var _favorite = MutableLiveData<Favorite?>()
    var favorite: LiveData<Favorite?> = _favorite

    fun insert(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(favorite)
        }
    }

    fun delete(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(favorite)
        }
    }

    fun getFavoriteUserByUser(user: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getFavoriteUserByUser(user)
            _favorite.postValue(response)
        }
    }
}