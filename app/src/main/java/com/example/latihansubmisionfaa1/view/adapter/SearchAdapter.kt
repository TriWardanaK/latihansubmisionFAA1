package com.example.latihansubmisionfaa1.view.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.latihansubmisionfaa1.databinding.ItemSearchBinding
import com.example.latihansubmisionfaa1.model.remote.response.SearchUserResponse
import com.example.latihansubmisionfaa1.view.DetailActivity

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var listSearch = ArrayList<SearchUserResponse>()

    @SuppressLint("NotifyDataSetChanged")
    fun setListSearch(listSearch: ArrayList<SearchUserResponse>) {
        this.listSearch = listSearch
        notifyDataSetChanged()
    }

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
        }
        init {
            itemView.setOnClickListener{
                val position = absoluteAdapterPosition
                if(position != RecyclerView.NO_POSITION) {
                    val user = listSearch[position].user
                    val intent = Intent(it.context, DetailActivity::class.java)
                    intent.putExtra("user", user)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}