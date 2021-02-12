package com.ericktijerou.topcoders.util

import com.google.android.material.appbar.AppBarLayout

abstract class AppBarStateChangeListener : AppBarLayout.OnOffsetChangedListener {
    private var mCurrentState = AppBarState.IDLE

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        when {
            verticalOffset == 0 -> {
                if (mCurrentState != AppBarState.EXPANDED) {
                    onStateChanged(appBarLayout, AppBarState.EXPANDED)
                }
                mCurrentState = AppBarState.EXPANDED
            }
            Math.abs(verticalOffset) >= appBarLayout.totalScrollRange -> {
                if (mCurrentState != AppBarState.COLLAPSED) {
                    onStateChanged(appBarLayout, AppBarState.COLLAPSED)
                }
                mCurrentState = AppBarState.COLLAPSED
            }
            else -> {
                if (mCurrentState != AppBarState.IDLE) {
                    onStateChanged(appBarLayout, AppBarState.IDLE)
                }
                mCurrentState = AppBarState.IDLE
            }
        }
    }

    abstract fun onStateChanged(appBarLayout: AppBarLayout, @AppBarState state: Int)
}
