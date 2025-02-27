package com.example.emotions.domain.model

data class EmotionFrequency(
    val id: Int,
    val emotion: Emotion,
    val amount: Int
)