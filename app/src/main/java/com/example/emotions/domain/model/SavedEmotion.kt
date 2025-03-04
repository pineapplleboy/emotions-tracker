package com.example.emotions.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SavedEmotion(
    val id: String,
    val type: String,
    val timeDate: String,
    val color: EmotionColor,
    val icon: Int
) : Parcelable