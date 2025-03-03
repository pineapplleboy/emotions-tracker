package com.example.emotions.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.emotions.R

@SuppressLint("ResourceAsColor")
class PageIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var page = 0
    var onPageChanged: ((Int) -> Unit)? = null

    private var views: List<View>

    init {
        inflate(context, R.layout.page_indicator, this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.PageIndicator)
            typedArray.recycle()
        }

        views = listOf(
            findViewById(R.id.first),
            findViewById(R.id.second),
            findViewById(R.id.third),
            findViewById(R.id.fourth)
        )

        views[page].setBackgroundResource(R.drawable.white_circle)

        for(i in 0..3) {
            views[i].setOnClickListener {
                setPage(i)
                onPageChanged?.invoke(page)
            }
        }
    }

    fun setPage(page: Int) {
        views[this.page].setBackgroundResource(R.drawable.light_gray_circle)
        this.page = page
        views[this.page].setBackgroundResource(R.drawable.white_circle)
    }
}