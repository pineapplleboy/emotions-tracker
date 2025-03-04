package com.example.emotions.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class EmotionColor : Parcelable {
    BLUE, RED, YELLOW, GREEN
}