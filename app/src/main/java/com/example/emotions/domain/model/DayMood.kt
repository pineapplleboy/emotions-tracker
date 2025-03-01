package com.example.emotions.domain.model

data class DayMood(
    val amount: Int,
    val redPercent: Float = 0f,
    val bluePercent: Float = 0f,
    val yellowPercent: Float = 0f,
    val greenPercent: Float = 0f,
)