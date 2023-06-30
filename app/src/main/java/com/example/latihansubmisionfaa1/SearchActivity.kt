package com.example.latihansubmisionfaa1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihansubmisionfaa1.adapter.SearchAdapter
import com.example.latihansubmisionfaa1.api.RetrofitClient
import com.example.latihansubmisionfaa1.databinding.ActivitySearchBinding
import com.example.latihansubmisionfaa1.response.SearchUserGithubResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_NAME = "extra_name"
    }

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showSearch()
    }

    fun dataEmpty(){
        binding.ivEmpty.visibility = View.VISIBLE
    }

    fun showLoading() {
        binding.pbSearch.visibility = View.VISIBLE
    }

    fun hiddenLoading(){
        binding.pbSearch.visibility = View.GONE
    }

    private fun getName(): String? {
        return intent.getStringExtra(EXTRA_NAME)
    }

    private fun showSearch() {
        binding.rvSearchUser.layoutManager = LinearLayoutManager(this)

        getName()?.let {
            RetrofitClient.instance.getSearchUser(it).enqueue(object :
                Callback<SearchUserGithubResponse> {
                override fun onFailure(call: Call<SearchUserGithubResponse>, t: Throwable) {
                    Toast.makeText(this@SearchActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<SearchUserGithubResponse>,
                    response: Response<SearchUserGithubResponse>
                ) {
                    showLoading()
                    val list = response.body()?.results
                    val searchUserAdapter = list?.let { it -> SearchAdapter(it) }
                    binding.rvSearchUser.adapter = searchUserAdapter
                    if (list?.size == 0) {
                        dataEmpty()
                    }
                    hiddenLoading()

                    searchUserAdapter?.setOnItemClickCallback(object :
                        SearchAdapter.OnItemClickCallback {
                        override fun onItemClicked(data: SearchUserGithubResponse.SearchUserResponse) {
                            showSearchUser(data)
                        }
                    })
                }
            })
        }
    }

    private fun showSearchUser(searchUserResponse: SearchUserGithubResponse.SearchUserResponse) {
        val moveObject = Intent(this@SearchActivity, DetailActivity::class.java)
        moveObject.putExtra(DetailActivity.EXTRA_NAME, searchUserResponse)
        startActivity(moveObject)
    }
}