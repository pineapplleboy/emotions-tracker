package com.example.emotions.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.emotions.R
import com.example.emotions.domain.model.EmotionColor
import com.example.emotions.domain.model.SavedEmotion
import com.example.emotions.presentation.viewholder.BlueEmotionViewHolder
import com.example.emotions.presentation.viewholder.EmotionViewHolder
import com.example.emotions.presentation.viewholder.GreenEmotionViewHolder
import com.example.emotions.presentation.viewholder.RedEmotionViewHolder
import com.example.emotions.presentation.viewholder.YellowEmotionViewHolder

class EmotionListAdapter : ListAdapter<SavedEmotion, EmotionViewHolder>(DIFF) {

    private companion object {
        val DIFF = object : DiffUtil.ItemCallback<SavedEmotion>() {
            override fun areItemsTheSame(
                oldItem: SavedEmotion,
                newItem: SavedEmotion
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: SavedEmotion,
                newItem: SavedEmotion
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).color.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmotionViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.emotion_card, parent, false)
        return when (viewType) {
            EmotionColor.BLUE.ordinal -> BlueEmotionViewHolder(view)
            EmotionColor.RED.ordinal -> RedEmotionViewHolder(view)
            EmotionColor.GREEN.ordinal -> GreenEmotionViewHolder(view)
            EmotionColor.YELLOW.ordinal -> YellowEmotionViewHolder(view)
            else -> throw IllegalArgumentException("Unknown Emotion Color")
        }
    }

    override fun onBindViewHolder(holder: EmotionViewHolder, position: Int) {
        val emotion = getItem(position)
        holder.bind(emotion)
    }
}