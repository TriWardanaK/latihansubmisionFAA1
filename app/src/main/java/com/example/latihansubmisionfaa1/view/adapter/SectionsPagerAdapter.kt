package com.example.latihansubmisionfaa1.view.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.latihansubmisionfaa1.view.viewmodel.follower.FollowersFragment
import com.example.latihansubmisionfaa1.view.viewmodel.favorite.FavoritesFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FavoritesFragment()
        }
        return fragment as Fragment
    }
}