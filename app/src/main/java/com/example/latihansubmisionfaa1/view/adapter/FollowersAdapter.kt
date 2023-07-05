package com.example.latihansubmisionfaa1.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.latihansubmisionfaa1.databinding.ItemFollowersBinding
import com.example.latihansubmisionfaa1.model.remote.response.FollowersGithubResponse

class FollowersAdapter : RecyclerView.Adapter<FollowersAdapter.ViewHolder>() {

    private var listFollowers = ArrayList<FollowersGithubResponse>()

    @SuppressLint("NotifyDataSetChanged")
    fun setListFollowers(listFollowers: ArrayList<FollowersGithubResponse>) {
        this.listFollowers = listFollowers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemFollowersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listFollowers.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listFollowers[position])
    }

    inner class ViewHolder(private var binding: ItemFollowersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(followersResponse: FollowersGithubResponse) {
            Glide.with(itemView.context)
                .load(followersResponse.avatar)
                .into(binding.ivUserGithub)

            binding.tvUsername.text = followersResponse.user

        }
    }
}