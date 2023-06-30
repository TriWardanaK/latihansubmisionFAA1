package com.example.latihansubmisionfaa1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihansubmisionfaa1.adapter.FollowersAdapter
import com.example.latihansubmisionfaa1.api.RetrofitClient
import com.example.latihansubmisionfaa1.databinding.FragmentFollowersBinding
import com.example.latihansubmisionfaa1.response.FollowersGithubResponse
import com.example.latihansubmisionfaa1.response.SearchUserGithubResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("DEPRECATION")
class FollowersFragment : Fragment() {

    companion object {
        const val EXTRA_NAME = "extra_name"
    }

    private lateinit var binding: FragmentFollowersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowersBinding.inflate(inflater, container, false)
        showFollowers()
        return binding.root

    }

    fun dataEmpty() {
        binding.ivEmpty.visibility = View.VISIBLE
    }

    fun showLoading() {
        binding.pbSearch.visibility = View.VISIBLE
    }

    fun hiddenLoading() {
        binding.pbSearch.visibility = View.GONE
    }

    private fun getUsername(): String? {
        val username =
            activity?.intent?.getParcelableExtra(EXTRA_NAME) as SearchUserGithubResponse.SearchUserResponse?
        return username?.user
    }

    private fun showFollowers() {
        binding.rvFollowers.layoutManager = LinearLayoutManager(context)

        getUsername()?.let {
            RetrofitClient.instance.getFollowers(it).enqueue(object :
                Callback<ArrayList<FollowersGithubResponse>> {
                override fun onFailure(call: Call<ArrayList<FollowersGithubResponse>>, t: Throwable) {
                    Toast.makeText(context, "${t.message}", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<ArrayList<FollowersGithubResponse>>,
                    response: Response<ArrayList<FollowersGithubResponse>>
                ) {
                    showLoading()
                    val list = response.body()
                    val followersAdapter = list?.let { it -> FollowersAdapter(it) }
                    binding.rvFollowers.adapter = followersAdapter
                    if (list?.size == 0) {
                        dataEmpty()
                    }
                    hiddenLoading()
                }
            })
        }
    }
}