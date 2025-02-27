package com.example.emotions.domain.model

data class Weekday(
    val id: Int,
    val day: String,
    val emotions: List<Emotion>
)