package com.example.emotions.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.emotions.R
import com.example.emotions.databinding.FragmentMoodStatsBinding
import com.example.emotions.domain.model.DayMood
import com.example.emotions.ui.view.DayMoodSegment

class MoodStatsFragment : Fragment() {

    private lateinit var binding: FragmentMoodStatsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoodStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dayMood = listOf(
            DayMood(
                amount = 4,
                redPercent = 0.25f,
                greenPercent = 0.25f,
                bluePercent = 0.25f,
                yellowPercent = 0.25f,
            ),
            DayMood(
                amount = 3,
                greenPercent = 0.33f,
                bluePercent = 0.33f,
                yellowPercent = 0.33f,
            ),
            DayMood(
                amount = 2,
                redPercent = 0.5f,
                greenPercent = 0.5f
            ),
            DayMood(
                amount = 1,
                redPercent = 1f
            ),
            DayMood(
                amount = 0
            ),
        )

        val moodBars = listOf(
            binding.earlyMorning,
            binding.morning,
            binding.afternoon,
            binding.evening,
            binding.lateEvening
        )

        for(i in 0..4) {
            moodBars[i].setMoodValues(
                red = dayMood[i].redPercent,
                blue = dayMood[i].bluePercent,
                green = dayMood[i].greenPercent,
                yellow = dayMood[i].yellowPercent,
            )
            moodBars[i].setAmount(dayMood[i].amount)
        }
    }
}