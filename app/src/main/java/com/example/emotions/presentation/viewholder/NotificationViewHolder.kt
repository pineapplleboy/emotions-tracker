package com.example.emotions.presentation.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.emotions.databinding.NotificationSettingsBinding
import com.example.emotions.domain.model.NotificationSettings

class NotificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = NotificationSettingsBinding.bind(view)

    fun bind(notificationSettings: NotificationSettings, onDelete: (NotificationSettings) -> Unit) =
        with(binding) {

            time.text = notificationSettings.time

            deleteButton.setOnClickListener {
                onDelete(notificationSettings)
            }
        }
}