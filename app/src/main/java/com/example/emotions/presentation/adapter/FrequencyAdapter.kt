package com.example.emotions.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.emotions.R
import com.example.emotions.domain.model.EmotionFrequency
import com.example.emotions.presentation.viewholder.FrequencyEmotionViewHolder

class FrequencyAdapter : ListAdapter<EmotionFrequency, FrequencyEmotionViewHolder>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<EmotionFrequency>() {
            override fun areContentsTheSame(
                oldItem: EmotionFrequency,
                newItem: EmotionFrequency
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(
                oldItem: EmotionFrequency,
                newItem: EmotionFrequency
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }

        var guidelinePosition: Int = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrequencyEmotionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.emotion_frequency_card, parent, false)
        return FrequencyEmotionViewHolder(view)
    }

    override fun onBindViewHolder(holder: FrequencyEmotionViewHolder, position: Int) {
        val emotion = getItem(position)
        holder.bind(
            emotion,
            guidelinePosition,
            emotion.amount.toFloat() / currentList.maxOf { it.amount })
    }
}