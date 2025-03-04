package com.example.emotions

import com.example.emotions.domain.model.CategoryPercents
import com.example.emotions.ui.fragment.CategoryStatsFragment
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KTextView

object CategoryStatsScreen: KScreen<CategoryStatsScreen>() {
    override val layoutId: Int = R.layout.fragment_category_stats
    override val viewClass = CategoryStatsFragment::class.java

    private val red = KTextView {
        withId(R.id.redPercent)
    }

    private val green = KTextView {
        withId(R.id.greenPercent)
    }

    private val blue = KTextView {
        withId(R.id.bluePercent)
    }

    private val yellow = KTextView {
        withId(R.id.yellowPercent)
    }

    fun checkTexts(percent: CategoryPercents) {
        red.isDisplayed()
        red.hasText("${percent.red}%")

        blue.isDisplayed()
        blue.hasText("${percent.blue}%")

        green.isDisplayed()
        green.hasText("${percent.green}%")

        yellow.isDisplayed()
        yellow.hasText("${percent.yellow}%")
    }
}