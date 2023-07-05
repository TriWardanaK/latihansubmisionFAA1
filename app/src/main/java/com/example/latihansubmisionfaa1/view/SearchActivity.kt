package com.example.latihansubmisionfaa1.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihansubmisionfaa1.R
import com.example.latihansubmisionfaa1.databinding.ActivitySearchBinding
import com.example.latihansubmisionfaa1.model.remote.response.SearchUserGithubResponse
import com.example.latihansubmisionfaa1.util.RequestState
import com.example.latihansubmisionfaa1.view.adapter.SearchAdapter
import com.example.latihansubmisionfaa1.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity() {

//    companion object {
//        const val EXTRA_NAME = "extra_name"
//    }

//    private lateinit var viewModel: SearchViewModel
//    private lateinit var binding: ActivitySearchBinding
//    private lateinit var recyclerAdapter: SearchAdapter

    private var _binding: ActivitySearchBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: SearchViewModel
    private lateinit var recyclerAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        dataEmpty()

//        observeAnychangeSearchMovie()
//        initRecyclerview()

//        initViewModel()
//        initRecyclerview()
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
//                    viewModel.setSearchQuery(it)
                    viewModel.searchUser(it)
                    observeAnychangeSearchMovie()
                    initRecyclerview()
//                    initViewModel()
//                    initRecyclerview()
                }
                return false
            }
        })
        return true
    }

//    @SuppressLint("NotifyDataSetChanged")
//    private fun initViewModel(){
//        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
//        viewModel.getLiveDataObserver().observe(this@SearchActivity){
//            recyclerAdapter.setListSearch(it)
//            recyclerAdapter.notifyDataSetChanged()
//        }
////        viewModel.loadShowSearch("a")
//        viewModel.isLoading.observe(this@SearchActivity) {
//            showLoading(it)
//        }
//        viewModel.isEmpty.observe(this@SearchActivity) {
//            dataEmpty(it)
//        }
//        viewModel.error.observe(this@SearchActivity){
//            Toast.makeText(this@SearchActivity, it.toString(), Toast.LENGTH_SHORT).show()
//        }
//    }

    private fun observeAnychangeSearchMovie() {
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
//        recyclerAdapter.setOnItemClickCallback(object :
//            SearchAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: SearchUserGithubResponse.SearchUserResponse) {
//                showSearchUser(data)
//            }
//        })
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

//
//    private fun showSearchUser(searchUserResponse: SearchUserGithubResponse.SearchUserResponse) {
//        val moveObject = Intent(this@SearchActivity, DetailActivity::class.java)
//        moveObject.putExtra(DetailActivity.EXTRA_NAME, searchUserResponse)
//        startActivity(moveObject)
//    }

//    private fun dataEmpty(isEmpty: Boolean) {
//        binding.ivEmpty.visibility = if (isEmpty) View.VISIBLE else View.GONE
//    }
//
//    private fun showLoading(isLoading: Boolean) {
//        binding.pbSearch.visibility = if (isLoading) View.VISIBLE else View.GONE
//    }

//    fun getName(): String{
//        return viewModel.dataSearch.value.toString()
//    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_search, menu)
//
//        val searchView: SearchView = menu?.findItem(R.id.search)?.actionView as SearchView
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                return false
//            }
//
//            @SuppressLint("NotifyDataSetChanged")
//            override fun onQueryTextChange(newText: String): Boolean {
//                newText.let {
////                    viewModel.setSearchQuery(it)
//                    viewModel.loadShowSearch(it)
//                    initViewModel()
//                    initRecyclerview()
//                }
//                return false
//            }
//        })
//        return true
//    }

//    fun getName(): String? {
//        return intent.getStringExtra(EXTRA_NAME)
//    }

//    private fun showSearch() {
//        binding.rvSearchUser.layoutManager = LinearLayoutManager(this)
//
//        getName()?.let {
//            RetrofitClient.instance.getSearchUser(it).enqueue(object :
//                Callback<SearchUserGithubResponse> {
//                override fun onFailure(call: Call<SearchUserGithubResponse>, t: Throwable) {
//                    Toast.makeText(this@SearchActivity, "${t.message}", Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onResponse(
//                    call: Call<SearchUserGithubResponse>,
//                    response: Response<SearchUserGithubResponse>
//                ) {
//                    showLoading()
//                    val list = response.body()?.results
//                    val searchUserAdapter = list?.let { it -> SearchAdapter(it) }
//                    binding.rvSearchUser.adapter = searchUserAdapter
//                    if (list?.size == 0) {
//                        dataEmpty()
//                    }
//                    hiddenLoading()
//
//                    searchUserAdapter?.setOnItemClickCallback(object :
//                        SearchAdapter.OnItemClickCallback {
//                        override fun onItemClicked(data: SearchUserGithubResponse.SearchUserResponse) {
//                            showSearchUser(data)
//                        }
//                    })
//                }
//            })
//        }
//    }

//    private fun showSearchUser(searchUserResponse: SearchUserGithubResponse.SearchUserResponse) {
//        val moveObject = Intent(this@SearchActivity, DetailActivity::class.java)
//        moveObject.putExtra(DetailActivity.EXTRA_NAME, searchUserResponse)
//        startActivity(moveObject)
//    }
}