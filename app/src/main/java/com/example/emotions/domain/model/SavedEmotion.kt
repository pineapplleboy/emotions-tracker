package com.example.emotions.domain.model

data class SavedEmotion(
    val id: String,
    val type: String,
    val timeDate: String,
    val color: EmotionColor
)