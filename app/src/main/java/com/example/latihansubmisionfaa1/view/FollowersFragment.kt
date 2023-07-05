package com.example.latihansubmisionfaa1.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihansubmisionfaa1.databinding.FragmentFollowersBinding
import com.example.latihansubmisionfaa1.model.remote.response.SearchUserGithubResponse
import com.example.latihansubmisionfaa1.view.adapter.FollowersAdapter
import com.example.latihansubmisionfaa1.viewmodel.FollowersFragmentViewModel

@Suppress("DEPRECATION")
class FollowersFragment : Fragment() {

    companion object {
        const val EXTRA_NAME = "extra_name"
    }

    private lateinit var viewModel: FollowersFragmentViewModel
    private lateinit var binding: FragmentFollowersBinding
    private lateinit var recyclerAdapter: FollowersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initRecyclerview()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel(){
        viewModel = ViewModelProvider(this)[FollowersFragmentViewModel::class.java]
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner){
            recyclerAdapter.setListFollowers(it)
            recyclerAdapter.notifyDataSetChanged()
        }
//        viewModel.showFollowers(this)
        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
        viewModel.isEmpty.observe(viewLifecycleOwner) {
            dataEmpty(it)
        }
    }

    private fun initRecyclerview() {
        binding.rvFollowers.layoutManager = LinearLayoutManager(context)
        recyclerAdapter = FollowersAdapter()
        binding.rvFollowers.adapter = recyclerAdapter
    }

    private fun dataEmpty(isEmpty: Boolean) {
        binding.ivEmpty.visibility = if (isEmpty) View.VISIBLE else View.GONE
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbSearch.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

//    fun getUsername(): String? {
//        val username =
//            activity?.intent?.getParcelableExtra(EXTRA_NAME) as SearchUserGithubResponse.SearchUserResponse?
//        return username?.user
//    }

//    private fun showFollowers() {
//        binding.rvFollowers.layoutManager = LinearLayoutManager(context)
//
//        getUsername()?.let {
//            RetrofitClient.instance.getFollowers(it).enqueue(object :
//                Callback<ArrayList<FollowersGithubResponse>> {
//                override fun onFailure(call: Call<ArrayList<FollowersGithubResponse>>, t: Throwable) {
//                    Toast.makeText(context, "${t.message}", Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onResponse(
//                    call: Call<ArrayList<FollowersGithubResponse>>,
//                    response: Response<ArrayList<FollowersGithubResponse>>
//                ) {
//                    showLoading()
//                    val list = response.body()
//                    val followersAdapter = list?.let { it -> FollowersAdapter(it) }
//                    binding.rvFollowers.adapter = followersAdapter
//                    if (list?.size == 0) {
//                        dataEmpty()
//                    }
//                    hiddenLoading()
//                }
//            })
//        }
//    }
}