package com.example.emotions

import android.view.View
import com.example.emotions.domain.model.EmotionColor
import com.example.emotions.domain.model.SavedEmotion
import com.example.emotions.ui.fragment.JournalFragment
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object JournalScreen: KScreen<JournalScreen>() {
    override val layoutId = R.layout.fragment_journal
    override val viewClass = JournalFragment::class.java

    private val recycler = KRecyclerView(
        builder = { withId(R.id.emotionsRecycler) },
        itemTypeBuilder = {
            itemType(::SavedEmotionItem)
        }
    )

    private val leftHalf = KImageView {
        withId(R.id.coloredLeftHalfCircle)
    }

    private val rightHalf = KImageView {
        withId(R.id.coloredRightHalfCircle)
    }

    fun checkRecycler(emotions: List<SavedEmotion>) {
        for(i in emotions.indices) {
            checkRecyclerItem(i, emotions[i])
        }
    }

    private fun checkRecyclerItem(position: Int, emotion: SavedEmotion) {

        recycler.scrollTo(position = position)

        recycler.childAt<SavedEmotionItem>(position) {

            scrollTo()

            emotionType.isDisplayed()
            emotionType.hasText(emotion.type)

            timeDate.isDisplayed()
            timeDate.hasText(emotion.timeDate)

            icon.isDisplayed()
//            icon.hasDrawable(emotion.icon)
        }
    }

    fun checkLeftHalf(color: EmotionColor?) {
        leftHalf.hasDrawable(getHalfColor(color))
    }

    fun checkRightHalf(color: EmotionColor?) {
        rightHalf.hasDrawable(getHalfColor(color))
    }

    private fun getHalfColor(color: EmotionColor?): Int {
        return when(color) {
            EmotionColor.RED -> R.drawable.half_circle_red
            EmotionColor.GREEN -> R.drawable.half_circle_green
            EmotionColor.BLUE -> R.drawable.half_circle_blue
            EmotionColor.YELLOW -> R.drawable.half_circle_yellow
            null -> android.R.color.transparent
        }
    }
}

class SavedEmotionItem(parent: Matcher<View>) : KRecyclerItem<SavedEmotionItem>(parent) {
    val emotionType = KTextView(parent) { withId(R.id.emotionType) }
    val timeDate = KTextView(parent) { withId(R.id.timeDate) }
    val icon = KImageView(parent) { withId(R.id.icon) }
}