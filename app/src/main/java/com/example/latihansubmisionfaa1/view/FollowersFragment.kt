package com.example.latihansubmisionfaa1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihansubmisionfaa1.databinding.FragmentFollowersBinding
import com.example.latihansubmisionfaa1.util.RequestState
import com.example.latihansubmisionfaa1.view.adapter.FollowersAdapter
import com.example.latihansubmisionfaa1.viewmodel.FollowersFragmentViewModel

class FollowersFragment : Fragment() {

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

        viewModel = ViewModelProvider(this)[FollowersFragmentViewModel::class.java]

        val activity = requireActivity() as DetailActivity
        val user = activity.getUser()

        user?.let { viewModel.getFollowersUser(it) }
        observeFollowersUser()
        initRecyclerview()
    }

    private fun observeFollowersUser() {
        viewModel.followersResponse.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    is RequestState.Loading -> showLoading()
                    is RequestState.Success -> {
                        hideLoading()
                        notEmpty()
                        it.data.let { data ->
                            if (data != null) {
                                recyclerAdapter.setListFollowers(data)
                            }
                        }
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
        binding.rvFollowers.layoutManager = LinearLayoutManager(context)
        recyclerAdapter = FollowersAdapter()
        binding.rvFollowers.adapter = recyclerAdapter
    }

    private fun dataEmpty() {
        binding.ivEmpty.visibility = View.VISIBLE
    }

    private fun notEmpty() {
        binding.ivEmpty.visibility = View.GONE
    }

    private fun showLoading() {
        binding.pbSearch.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.pbSearch.visibility = View.GONE
    }
}