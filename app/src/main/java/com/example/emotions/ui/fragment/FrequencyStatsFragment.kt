package com.example.emotions.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emotions.R
import com.example.emotions.databinding.FragmentFrequencyStatsBinding
import com.example.emotions.domain.model.Emotion
import com.example.emotions.domain.model.EmotionColor
import com.example.emotions.domain.model.EmotionFrequency
import com.example.emotions.presentation.adapter.FrequencyAdapter
import com.example.emotions.presentation.adapter.WeekDayAdapter

class FrequencyStatsFragment : Fragment() {

    private lateinit var binding: FragmentFrequencyStatsBinding
    private lateinit var adapter: FrequencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFrequencyStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emotions = listOf(
            Emotion("Ярость", "Сильное чувство гнева, сопровождающееся агрессией", EmotionColor.RED, R.drawable.ic_rage),
            Emotion("Напряжение", "Ощущение стресса и внутреннего давления", EmotionColor.RED, R.drawable.ic_stress),
            Emotion("Возбуждение", "Состояние повышенной активности и бодрствования", EmotionColor.YELLOW, R.drawable.ic_excitement),
            Emotion("Восторг", "Интенсивное чувство радости и восхищения", EmotionColor.YELLOW, R.drawable.ic_delight),
            Emotion("Зависть", "Желание иметь то, что есть у других, с оттенком недовольства", EmotionColor.RED, R.drawable.ic_envy),
            Emotion("Беспокойство", "Чувство тревоги и волнения о чём-либо", EmotionColor.RED, R.drawable.ic_anxiety),
            Emotion("Уверенность", "Ощущение внутренней силы и веры в свои способности", EmotionColor.YELLOW, R.drawable.ic_confidence),
            Emotion("Счастье", "Общее состояние удовлетворения и радости", EmotionColor.YELLOW, R.drawable.ic_happinness),
            Emotion("Выгорание", "Чувство эмоционального и физического истощения", EmotionColor.BLUE, R.drawable.ic_burnout),
            Emotion("Усталость", "Ощущение, что необходимо отдохнуть", EmotionColor.BLUE, R.drawable.ic_fatigue),
            Emotion("Спокойствие", "Состояние внутреннего равновесия и умиротворённости", EmotionColor.GREEN, R.drawable.ic_calm),
            Emotion("Удовлетворённость", "Чувство довольства достигнутым", EmotionColor.GREEN, R.drawable.ic_satisfaction),
            Emotion("Депрессия", "Состояние подавленности, потери интереса и энергии", EmotionColor.BLUE, R.drawable.ic_depression),
            Emotion("Апатия", "Безразличие к окружающему, отсутствие мотивации", EmotionColor.BLUE, R.drawable.ic_apathy),
            Emotion("Благодарность", "Чувство признательности за что-то хорошее", EmotionColor.GREEN, R.drawable.ic_gratitude),
            Emotion("Защищённость", "Ощущение безопасности и комфорта", EmotionColor.GREEN, R.drawable.ic_security)
        )

        val emotionsFrequency = listOf(
            EmotionFrequency(
                0, emotions[14], 3
            ),
            EmotionFrequency(
                0, emotions[1], 2
            ),EmotionFrequency(
                0, emotions[5], 2
            ),
            EmotionFrequency(
                0, emotions[8], 1
            ),
        )

        val longestText = emotionsFrequency.map { it.emotion.type }.maxByOrNull { it.length } ?: ""
        val tempTextView = TextView(this.context).apply {
            text = longestText
            textSize = 16f
            measure(0, 0)
        }

        FrequencyAdapter.guidelinePosition = tempTextView.measuredWidth

        adapter = FrequencyAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        adapter.submitList(emotionsFrequency)
    }
}