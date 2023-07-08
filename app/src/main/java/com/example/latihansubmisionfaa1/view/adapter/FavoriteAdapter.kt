package com.example.latihansubmisionfaa1.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.latihansubmisionfaa1.databinding.ItemFavoritesBinding
import com.example.latihansubmisionfaa1.model.local.database.favorite.Favorite

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private var listFavorite = ArrayList<Favorite>()

    @SuppressLint("NotifyDataSetChanged")
    fun setListFavorite(listFavorite: ArrayList<Favorite>) {
        this.listFavorite = listFavorite
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemFavoritesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listFavorite.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listFavorite[position])
    }

    inner class ViewHolder(private var binding: ItemFavoritesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(followersResponse: Favorite) {
            Glide.with(itemView.context)
                .load(followersResponse.avatar)
                .into(binding.ivUserGithub)

            binding.tvUsername.text = followersResponse.user

        }
    }
}