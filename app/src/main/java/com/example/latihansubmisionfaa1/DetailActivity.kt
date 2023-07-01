package com.example.latihansubmisionfaa1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import com.example.latihansubmisionfaa1.adapter.SectionsPagerAdapter
import com.example.latihansubmisionfaa1.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_NAME = "extra_name"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }
}