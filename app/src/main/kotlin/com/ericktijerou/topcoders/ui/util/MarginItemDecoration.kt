package com.ericktijerou.topcoders.ui.util

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(@DimenRes private val margin: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val marginInPx = parent.resources.getDimension(margin).toInt()
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = marginInPx
            }
            bottom = marginInPx
        }
    }
}