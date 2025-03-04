package com.example.emotions

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.emotions.domain.model.EmotionColor
import com.example.emotions.domain.model.SavedEmotion
import com.example.emotions.ui.fragment.JournalFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JournalFragmentTest {

    private val emotions = listOf(
        SavedEmotion("1", "Счастье", "вчера, 20:30", EmotionColor.YELLOW, R.drawable.ic_happinness),
        SavedEmotion("2", "Грусть 2", "вчера, 20:30", EmotionColor.BLUE, R.drawable.ic_fatigue),
        SavedEmotion("3", "Злоба", "вчера, 20:30", EmotionColor.RED, R.drawable.ic_rage),
        SavedEmotion("4", "Спокойствие", "вчера, 20:30", EmotionColor.GREEN, R.drawable.ic_calm)
    )

    private val leftHalf: EmotionColor? = EmotionColor.RED
    private val rightHalf: EmotionColor? = null

    @Test
    fun journalTest() {
        launchFragment()
        JournalScreen.checkRecycler(emotions)
//        JournalScreen.checkLeftHalf(leftHalf)
//        JournalScreen.checkRightHalf(rightHalf)
    }

    private fun launchFragment() {
        val fragment = JournalFragment.newInstance(emotions, rightHalf, leftHalf)
        FragmentScenario.launchInContainer(
            fragmentClass = JournalFragment::class.java,
            themeResId = R.style.Theme_Emotions,
            fragmentArgs = fragment.arguments
        )
    }
}