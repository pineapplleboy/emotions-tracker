package com.example.emotions.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.emotions.databinding.FragmentStatsBinding
import com.example.emotions.presentation.adapter.StatsAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class StatsFragment : Fragment() {
    private lateinit var binding: FragmentStatsBinding

    private lateinit var adapter: StatsAdapter
    private lateinit var tabTitles: List<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabTitles = listOf(
            "17-23 фев",
            "10-16 фев",
            "3-9 фев",
            "27 янв - 2 фев",
            "20-26 янв"
        )

        adapter = StatsAdapter(this, tabTitles)
        binding.pager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}