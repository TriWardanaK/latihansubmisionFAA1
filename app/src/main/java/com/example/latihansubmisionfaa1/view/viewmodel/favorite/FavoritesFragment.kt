package com.example.latihansubmisionfaa1.view.viewmodel.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihansubmisionfaa1.databinding.FragmentFavoritesBinding
import com.example.latihansubmisionfaa1.model.local.database.favorite.Favorite
import com.example.latihansubmisionfaa1.view.adapter.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private val viewModel: FavoriteFragmentViewModel by viewModels()

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var recyclerAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFavoriteUser()
        initRecyclerview()
    }

    private fun observeFavoriteUser() {
        viewModel.getAllFavorites.observe(viewLifecycleOwner) {
            if (it != null) {
                recyclerAdapter.setListFavorite(it as ArrayList<Favorite>)
            }
        }
    }

    private fun initRecyclerview() {
        binding.rvFavorite.layoutManager = LinearLayoutManager(context)
        recyclerAdapter = FavoriteAdapter()
        binding.rvFavorite.adapter = recyclerAdapter
    }
}