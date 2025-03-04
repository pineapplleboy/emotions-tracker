package com.example.emotions.presentation.viewholder

import android.annotation.SuppressLint
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.emotions.R
import com.example.emotions.databinding.EmotionFrequencyCardBinding
import com.example.emotions.domain.model.EmotionColor
import com.example.emotions.domain.model.EmotionFrequency

class FrequencyEmotionViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val binding = EmotionFrequencyCardBinding.bind(view)

    private var id: Int = 0

    @SuppressLint("SetTextI18n")
    fun bind(emotionFrequency: EmotionFrequency, guidelinePosition: Int, frequency: Float) =
        with(binding) {
            id = emotionFrequency.id

            val dpToPx = view.context.resources.displayMetrics.density

            emotion.post {
                val params = bar.layoutParams as ConstraintLayout.LayoutParams
                val minMargin = (params.marginStart * dpToPx).toInt()

                params.marginStart = minMargin + guidelinePosition - emotion.width
                bar.layoutParams = params
            }

            imageView.setImageResource(emotionFrequency.emotion.icon)
            emotion.text = emotionFrequency.emotion.type

            (coloredPart.layoutParams as LinearLayout.LayoutParams).weight = frequency
            (restPart.layoutParams as LinearLayout.LayoutParams).weight = 1 - frequency

            coloredPart.setBackgroundResource(getBarBackground(emotionFrequency.emotion.color))
            emotionAmount.text = emotionFrequency.amount.toString()
        }

    private fun getBarBackground(color: EmotionColor): Int {
        return when (color) {
            EmotionColor.RED -> R.drawable.gradient_bar_red
            EmotionColor.GREEN -> R.drawable.gradient_bar_green
            EmotionColor.YELLOW -> R.drawable.gradient_bar_yellow
            EmotionColor.BLUE -> R.drawable.gradient_bar_blue
        }
    }
}