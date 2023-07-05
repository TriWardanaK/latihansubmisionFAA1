package com.example.latihansubmisionfaa1.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.latihansubmisionfaa1.databinding.ItemSearchBinding
import com.example.latihansubmisionfaa1.model.remote.response.SearchUserGithubResponse
import com.example.latihansubmisionfaa1.model.remote.response.SearchUserResponse

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var listSearch = ArrayList<SearchUserResponse>()

    @SuppressLint("NotifyDataSetChanged")
    fun setListSearch(listSearch: ArrayList<SearchUserResponse>) {
        this.listSearch = listSearch
        notifyDataSetChanged()
    }

//    private var listSearch: ArrayList<SearchUserResponse>? = null
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun setListSearch(listSearch: ArrayList<SearchUserResponse>) {
//        this.listSearch = listSearch
//        notifyDataSetChanged()
//    }

//    private var onItemClickCallback: OnItemClickCallback? = null
//
//    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
//        this.onItemClickCallback = onItemClickCallback
//    }

//    private val differCallback = object: DiffUtil.ItemCallback<SearchUserResponse>() {
//        override fun areItemsTheSame(oldItem: SearchUserResponse, newItem: SearchUserResponse):
//                Boolean = oldItem == newItem
//        override fun areContentsTheSame(oldItem: SearchUserResponse, newItem: SearchUserResponse):
//                Boolean = oldItem == newItem
//    }
//
//    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listSearch.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listSearch[position])
    }

    inner class ViewHolder(private var binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(searchUserResponse: SearchUserResponse) {
            Glide.with(itemView.context)
                .load(searchUserResponse.avatar)
                .into(binding.ivUserGithub)

            binding.tvUsername.text = searchUserResponse.user

//            itemView.setOnClickListener{
//                onItemClickCallback?.onItemClicked(searchUserResponse)
//            }
        }
    }

//    interface OnItemClickCallback{
//        fun onItemClicked(data: SearchUserGithubResponse.SearchUserResponse)
//    }
}