package com.sxiaozhi.somking.core.base.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class HorizontalSpaceItemDecoration(
    private val horizontalSpaceWidth: Int
) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition: Int = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0
        if (itemPosition < itemCount - 1) {
            outRect.right = horizontalSpaceWidth
        }
    }
}
