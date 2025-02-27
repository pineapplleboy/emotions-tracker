package com.example.emotions.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class StatsPagerAdapter(fragment: Fragment, private val fragments: List<Fragment>) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment = fragments[position]
}