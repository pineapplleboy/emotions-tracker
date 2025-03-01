package com.example.emotions.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.emotions.R
import com.example.emotions.databinding.FragmentStatsPageBinding
import com.example.emotions.presentation.adapter.StatsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class StatsPageFragment : Fragment() {

    private lateinit var binding: FragmentStatsPageBinding

    companion object {
        private const val ARG_TITLE = "tab_title"

        fun newInstance(title: String): StatsPageFragment {
            return StatsPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatsPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragments = listOf(
            CategoryStatsFragment(),
            WeekStatsFragment(),
            FrequencyStatsFragment(),
            MoodStatsFragment()
        )

        binding.pager.adapter = StatsPagerAdapter(this, fragments)
        binding.pager.orientation = ViewPager2.ORIENTATION_VERTICAL
    }
}