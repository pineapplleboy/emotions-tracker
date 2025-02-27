package com.example.emotions.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.emotions.R
import com.example.emotions.domain.model.Weekday
import com.example.emotions.presentation.viewholder.WeekDayViewHolder

class WeekDayAdapter: ListAdapter<Weekday, WeekDayViewHolder>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Weekday>() {
            override fun areItemsTheSame(
                oldItem: Weekday,
                newItem: Weekday
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Weekday,
                newItem: Weekday
            ): Boolean {
                return oldItem == newItem
            }
        }

        var guidelinePosition: Int = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekDayViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.weekday_card, parent, false)
        return WeekDayViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeekDayViewHolder, position: Int) {
        val weekday = getItem(position)
        holder.bind(weekday, guidelinePosition)
    }
}