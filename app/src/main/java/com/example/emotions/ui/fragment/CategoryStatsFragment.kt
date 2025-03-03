package com.example.emotions.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.emotions.R
import com.example.emotions.databinding.FragmentCategoryStatsBinding

class CategoryStatsFragment : Fragment() {

    private lateinit var binding: FragmentCategoryStatsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emptyView.post {
            scaleCircle(binding.redCircle, binding.redPercent, 10)
            scaleCircle(binding.greenCircle, binding.greenPercent, 60)
            scaleCircle(binding.blueCircle, binding.bluePercent, 0)
            scaleCircle(binding.yellowCircle, binding.yellowPercent, 30)
        }

        binding.recordsAmount.text = resources.getQuantityString(R.plurals.records, 1, 1)
    }

    @SuppressLint("SetTextI18n")
    private fun scaleCircle(view: View, text: TextView, percent: Int) {

        val maxWidth = binding.emptyView.width
        val maxHeight = binding.emptyView.height

        val newSize = (maxHeight * percent * 1.15 / 100).toInt().coerceIn(0, maxWidth)

        val layoutParams = view.layoutParams
        layoutParams.width = newSize
        layoutParams.height = newSize
        view.layoutParams = layoutParams

        text.text = "${if (percent != 0) percent else ""}%"
    }
}