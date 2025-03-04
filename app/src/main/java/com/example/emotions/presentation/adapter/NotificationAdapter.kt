package com.example.emotions.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.emotions.R
import com.example.emotions.domain.model.NotificationSettings
import com.example.emotions.presentation.viewholder.NotificationViewHolder

class NotificationAdapter(
    private val onDelete: (NotificationSettings) -> Unit
) : ListAdapter<NotificationSettings, NotificationViewHolder>(DIFF) {

    private companion object {
        val DIFF = object : DiffUtil.ItemCallback<NotificationSettings>() {
            override fun areItemsTheSame(
                oldItem: NotificationSettings,
                newItem: NotificationSettings
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: NotificationSettings,
                newItem: NotificationSettings
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = getItem(position)
        holder.bind(notification, onDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notification_settings, parent, false)
        return NotificationViewHolder(view)
    }
}