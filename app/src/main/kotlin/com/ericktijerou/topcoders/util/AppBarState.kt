package com.ericktijerou.topcoders.util

import androidx.annotation.IntDef

@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@IntDef(
    AppBarState.EXPANDED,
    AppBarState.COLLAPSED,
    AppBarState.IDLE
)

annotation class AppBarState {
    companion object {
        const val EXPANDED = 0
        const val COLLAPSED = 1
        const val IDLE = 2
    }
}