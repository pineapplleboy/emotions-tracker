package com.example.emotions.domain.model

import java.util.UUID

data class NotificationSettings(
    val time: String,
    val id: UUID = UUID.randomUUID()
)