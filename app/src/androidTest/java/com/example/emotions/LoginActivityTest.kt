package com.example.emotions

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.emotions.ui.activity.LoginActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Test
    fun loginTest() {
        launchActivity()

        LoginActivityScreen.checkText("Добро пожаловать")
    }

    private fun launchActivity() {
        ActivityScenario.launch(LoginActivity::class.java)
    }
}