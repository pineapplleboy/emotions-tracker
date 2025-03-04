package com.example.emotions

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.emotions.domain.model.CategoryPercents
import com.example.emotions.ui.fragment.CategoryStatsFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryStatsTest {

    private val percents = CategoryPercents(10, 10, 40, 40)

    @Test
    fun categoryStatsTest() {
        launchFragment()
        CategoryStatsScreen.checkTexts(percents)
    }

    private fun launchFragment() {
        val fragment = CategoryStatsFragment.newInstance(percents)
        FragmentScenario.launchInContainer(
            fragmentClass = CategoryStatsFragment::class.java,
            themeResId = R.style.Theme_Emotions,
            fragmentArgs = fragment.arguments
        )
    }
}