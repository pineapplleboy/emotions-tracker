package com.example.emotions.presentation.viewholder

import android.view.View
import android.view.View.GONE
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.emotions.databinding.WeekdayCardBinding
import com.example.emotions.domain.model.Weekday

class WeekDayViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val binding = WeekdayCardBinding.bind(view)

    private var id: Int? = null

    fun bind(weekday: Weekday, guidelinePosition: Int) = with(binding) {

        id = weekday.id

        day.text = weekday.day
        emotions.text = weekday.emotions.joinToString(separator = "\n") { it.type }

        val dpToPx = view.context.resources.displayMetrics.density

        day.post {
            val params = emotions.layoutParams as ConstraintLayout.LayoutParams
            val minMargin = (params.marginStart * dpToPx).toInt()

            params.marginStart = minMargin + guidelinePosition - day.width
            emotions.layoutParams = params
        }

        if(weekday.emotions.isNotEmpty()) {
            placeHolder.visibility = GONE
        }

        val iconIds = mutableListOf<Int>()

        weekday.emotions.forEachIndexed { _, emotion ->
            val iconView = ImageView(view.context).apply {
                id = View.generateViewId()
                layoutParams =
                    ConstraintLayout.LayoutParams((40 * dpToPx).toInt(), (40 * dpToPx).toInt())
                setImageResource(emotion.icon)
                scaleType = ImageView.ScaleType.CENTER_INSIDE
            }

            main.addView(iconView)
            iconIds.add(iconView.id)
        }

        emotionFlow.referencedIds = iconIds.toIntArray()
    }
}