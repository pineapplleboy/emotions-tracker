package com.example.emotions.presentation.viewholder

import android.annotation.SuppressLint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.emotions.R
import com.example.emotions.databinding.EmotionCardBinding
import com.example.emotions.domain.model.SavedEmotion

abstract class EmotionViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val binding = EmotionCardBinding.bind(view)

    private var id: String? = null

    fun bind(savedEmotion: SavedEmotion) = with(binding) {

        id = savedEmotion.id

        emotionType.text = savedEmotion.type
        timeDate.text = savedEmotion.timeDate
        icon.setImageResource(savedEmotion.icon)

        changeColors()
    }

    open fun changeColors() {}
}

class BlueEmotionViewHolder(view: View) : EmotionViewHolder(view) {

    override fun changeColors() = with(binding) {
        card.setBackgroundResource(R.drawable.gradient_card_blue)
        emotionType.setTextColor(ContextCompat.getColor(view.context, R.color.sky_blue))
    }
}

class GreenEmotionViewHolder(view: View) : EmotionViewHolder(view) {

    override fun changeColors() = with(binding) {
        card.setBackgroundResource(R.drawable.gradient_card_green)
        emotionType.setTextColor(ContextCompat.getColor(view.context, R.color.light_green))
    }
}

class RedEmotionViewHolder(view: View) : EmotionViewHolder(view) {

    @SuppressLint("ResourceAsColor")
    override fun changeColors() = with(binding) {
        card.setBackgroundResource(R.drawable.gradient_card_red)
        emotionType.setTextColor(ContextCompat.getColor(view.context, R.color.scarlet_red))
    }
}

class YellowEmotionViewHolder(view: View) : EmotionViewHolder(view) {

    @SuppressLint("ResourceAsColor")
    override fun changeColors() = with(binding) {
        card.setBackgroundResource(R.drawable.gradient_card_yellow)
        emotionType.setTextColor(ContextCompat.getColor(view.context, R.color.honey_yellow))
    }
}