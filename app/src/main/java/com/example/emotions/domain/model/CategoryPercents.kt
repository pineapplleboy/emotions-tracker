package com.example.emotions.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryPercents(
    val red: Int,
    val green: Int,
    val blue: Int,
    val yellow: Int,
) : Parcelable