package com.example.latihansubmisionfaa1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.latihansubmisionfaa1.databinding.ItemSearchBinding
import com.example.latihansubmisionfaa1.response.SearchUserGithubResponse

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.ViewHolder>(){

    private var listSearch: ArrayList<SearchUserGithubResponse.SearchUserResponse>? = null

    fun setListSearch(listSearch: ArrayList<SearchUserGithubResponse.SearchUserResponse>?){
        this.listSearch = listSearch
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (listSearch == null) 0
        else listSearch?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listSearch!![position])
    }

    inner class ViewHolder(private var binding: ItemSearchBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (searchUserResponse: SearchUserGithubResponse.SearchUserResponse){
            Glide.with(itemView.context)
                .load(searchUserResponse.avatar)
                .into(binding.ivUserGithub)

            binding.tvUsername.text = searchUserResponse.user

            itemView.setOnClickListener{
                onItemClickCallback?.onItemClicked(searchUserResponse)
            }
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: SearchUserGithubResponse.SearchUserResponse)
    }
}