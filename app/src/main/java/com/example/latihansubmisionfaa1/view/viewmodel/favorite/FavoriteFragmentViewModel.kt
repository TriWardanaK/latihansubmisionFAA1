package com.example.latihansubmisionfaa1.view.viewmodel.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.latihansubmisionfaa1.model.local.database.favorite.Favorite
import com.example.latihansubmisionfaa1.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteFragmentViewModel @Inject constructor(repository: FavoriteRepository) : ViewModel() {

    val getAllFavorites: LiveData<List<Favorite>> = repository.getAllFavorites
}