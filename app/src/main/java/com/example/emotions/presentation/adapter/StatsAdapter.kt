package com.example.emotions.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.emotions.ui.fragment.StatsPageFragment

class StatsAdapter(fragment: Fragment, private val tabTitles: List<String>) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = tabTitles.size

    override fun createFragment(position: Int): Fragment {
        return StatsPageFragment.newInstance(tabTitles[position])
    }
}