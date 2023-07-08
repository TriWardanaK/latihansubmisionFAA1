package com.example.latihansubmisionfaa1.view.viewmodel.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.latihansubmisionfaa1.R
import com.example.latihansubmisionfaa1.databinding.ActivityDetailBinding
import com.example.latihansubmisionfaa1.model.local.database.favorite.Favorite
import com.example.latihansubmisionfaa1.model.local.database.favorite.FavoriteRoomDatabase
import com.example.latihansubmisionfaa1.view.adapter.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private var isFavorite: Boolean = false
    private var menu: Menu? = null
    private var favoriteItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FavoriteRoomDatabase.getInstance(applicationContext).favoriteDao()

        initPager()
        getFavoriteUser()
        observeFavoriteStatus()
    }

    private fun getFavoriteUser(){
        viewModel.getFavoriteUserByUser(getUser().toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        favoriteItem = menu?.findItem(R.id.action_favorite)
        updateFavoriteIcon()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                if (isFavorite) {
                    val favorite = Favorite(getUser().toString(), getAvatar())
                    viewModel.delete(favorite)
                    isFavorite = false
                    updateFavoriteIcon()
                } else {
                    val favorite = Favorite(getUser().toString(), getAvatar())
                    viewModel.insert(favorite)
                    isFavorite = true
                    updateFavoriteIcon()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun observeFavoriteStatus() {
        viewModel.favorite.observe(this) { favorite ->
            isFavorite = favorite != null
            updateFavoriteIcon()
        }
    }

    private fun updateFavoriteIcon() {
        favoriteItem?.setIcon(if (isFavorite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24)
    }

    private fun initPager() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    fun getUser(): String? {
        return intent.getStringExtra("user")
    }

    fun getAvatar(): String? {
        return intent.getStringExtra("avatar")
    }
}