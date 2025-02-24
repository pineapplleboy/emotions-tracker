package com.example.emotions.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.emotions.R
import com.google.android.material.chip.ChipGroup

class QuestionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val questionText: TextView
    private val chipGroup: ChipGroup

    init {
        inflate(context, R.layout.view_question, this)

        questionText = findViewById(R.id.questionText)
        chipGroup = findViewById(R.id.chipGroup)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.QuestionView)
            val text = typedArray.getString(R.styleable.QuestionView_questionText) ?: ""
            setQuestionText(text)
            typedArray.recycle()
        }
    }

    fun setQuestionText(text: String) {
        questionText.text = text
    }

    fun getChipGroup(): ChipGroup = chipGroup
}
