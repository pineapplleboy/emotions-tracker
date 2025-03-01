package com.example.emotions.presentation

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) >= 0 && parent.getChildAdapterPosition(view) < state.itemCount - 1) {
            outRect.bottom = space
        }
    }
}

fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()