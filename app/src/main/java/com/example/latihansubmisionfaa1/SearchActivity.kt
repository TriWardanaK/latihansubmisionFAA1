package com.example.latihansubmisionfaa1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihansubmisionfaa1.adapter.SearchAdapter
import com.example.latihansubmisionfaa1.databinding.ActivitySearchBinding
import com.example.latihansubmisionfaa1.response.SearchUserGithubResponse

class SearchActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
    }

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: ActivitySearchBinding
    private lateinit var recyclerAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initRecyclerview()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        viewModel.getLiveDataObserver().observe(this) {
            recyclerAdapter.setListSearch(it)
            recyclerAdapter.notifyDataSetChanged()
        }
        viewModel.showSearch(this)
        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
        viewModel.isEmpty.observe(this) {
            dataEmpty(it)
        }
    }

    private fun initRecyclerview() {
        binding.rvSearchUser.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = SearchAdapter()
        binding.rvSearchUser.adapter = recyclerAdapter
        recyclerAdapter.setOnItemClickCallback(object :
            SearchAdapter.OnItemClickCallback {
            override fun onItemClicked(data: SearchUserGithubResponse.SearchUserResponse) {
                showSearchUser(data)
            }
        })
    }

    private fun showSearchUser(searchUserResponse: SearchUserGithubResponse.SearchUserResponse) {
        val moveObject = Intent(this@SearchActivity, DetailActivity::class.java)
        moveObject.putExtra(DetailActivity.EXTRA_NAME, searchUserResponse)
        startActivity(moveObject)
    }

    private fun dataEmpty(isEmpty: Boolean) {
        binding.ivEmpty.visibility = if (isEmpty) View.VISIBLE else View.GONE
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbSearch.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    fun getName(): String? {
        return intent.getStringExtra(EXTRA_NAME)
    }

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