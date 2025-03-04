package com.example.emotions

import com.example.emotions.ui.activity.LoginActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KTextView

object LoginActivityScreen: KScreen<LoginActivityScreen>() {

    override val layoutId = R.layout.activity_login
    override val viewClass = LoginActivity::class.java

    private val text = KTextView { withId(R.id.welcomeText) }

    fun checkText(input: String) {
        text {
            isDisplayed()
            hasText(input)
        }
    }
}