package com.example.emotions.domain.model

data class Emotion(
    val type: String,
    val description: String,
    val color: EmotionColor,
    val icon: Int
)