package com.ericktijerou.topcoders.ui.util

import android.content.Context
import android.graphics.PointF
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class ScrollingLinearLayoutManager @JvmOverloads constructor(
    context: Context,
    orientation: Int = RecyclerView.VERTICAL,
    reverseLayout: Boolean = false
) : LinearLayoutManager(context, orientation, reverseLayout) {

    override fun smoothScrollToPosition(
        recyclerView: RecyclerView,
        state: RecyclerView.State?,
        position: Int
    ) {
        val smoothScroller = object : TopSnappedSmoothScroller(recyclerView.context) {
            override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float =
                MILLISECONDS_PER_INCH / displayMetrics.densityDpi

            override fun calculateTimeForScrolling(dx: Int): Int =
                super.calculateTimeForScrolling(dx).coerceAtMost(MAX_SCROLL_DURATION_MS)
        }
        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }

    private open inner class TopSnappedSmoothScroller(context: Context) :
        LinearSmoothScroller(context) {

        override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
            return this@ScrollingLinearLayoutManager
                .computeScrollVectorForPosition(targetPosition)
        }

        override fun getVerticalSnapPreference(): Int {
            return SNAP_TO_START
        }
    }

    companion object {
        private const val MILLISECONDS_PER_INCH = 100f
        private const val MAX_SCROLL_DURATION_MS = 150
    }
}