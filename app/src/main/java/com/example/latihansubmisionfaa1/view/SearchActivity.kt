package com.example.latihansubmisionfaa1.view

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihansubmisionfaa1.R
import com.example.latihansubmisionfaa1.databinding.ActivitySearchBinding
import com.example.latihansubmisionfaa1.util.RequestState
import com.example.latihansubmisionfaa1.view.adapter.SearchAdapter
import com.example.latihansubmisionfaa1.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity() {

    private var binding: ActivitySearchBinding? = null
    private lateinit var viewModel: SearchViewModel
    private lateinit var recyclerAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        dataEmpty()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchView: SearchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                newText.let {
                    viewModel.searchUser(it)
                    observeAnyChangeSearchMovie()
                    initRecyclerview()
                }
                return false
            }
        })
        return true
    }

    private fun observeAnyChangeSearchMovie() {
        viewModel.searchResponse.observe(this) {
            if (it != null) {
                when (it) {
                    is RequestState.Loading -> showLoading()
                    is RequestState.Success -> {
                        hideLoading()
                        notEmpty()
                        it.data?.results?.let { data -> recyclerAdapter.setListSearch(data) }
                    }
                    is RequestState.Error -> {
                        hideLoading()
                        dataEmpty()
                    }
                }
            }
        }
    }

    private fun initRecyclerview() {
        binding?.rvSearchUser?.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = SearchAdapter()
        binding?.rvSearchUser?.adapter = recyclerAdapter
    }

    private fun dataEmpty() {
        binding?.ivEmpty?.visibility = View.VISIBLE
    }

    private fun notEmpty() {
        binding?.ivEmpty?.visibility = View.GONE
    }

    private fun showLoading() {
        binding?.pbSearch?.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding?.pbSearch?.visibility = View.GONE
    }
}