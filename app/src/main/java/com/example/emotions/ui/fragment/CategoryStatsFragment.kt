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
import com.example.emotions.domain.model.CategoryPercents
import com.example.emotions.domain.model.EmotionColor
import com.example.emotions.domain.model.SavedEmotion

class CategoryStatsFragment : Fragment() {

    private lateinit var binding: FragmentCategoryStatsBinding

    private lateinit var percents: CategoryPercents

    companion object {
        private const val ARG_PERCENTS = "arg_percents"
        fun newInstance(
            percents: CategoryPercents
        ): CategoryStatsFragment {
            return CategoryStatsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PERCENTS, percents)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        percents = arguments?.getParcelable(ARG_PERCENTS) ?: CategoryPercents(0, 0,0, 0)
    }

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
            scaleCircle(binding.redCircle, binding.redPercent, percents.red)
            scaleCircle(binding.greenCircle, binding.greenPercent, percents.green)
            scaleCircle(binding.blueCircle, binding.bluePercent, percents.blue)
            scaleCircle(binding.yellowCircle, binding.yellowPercent, percents.yellow)
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

        text.text = if (percent != 0) "${percent}%" else ""
    }
}