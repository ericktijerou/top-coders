package com.ericktijerou.topcoders.ui.util

import androidx.annotation.IntDef

@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@IntDef(
    HomePageType.NONE,
    HomePageType.HOME_CODER,
    HomePageType.HOME_REPO,
)

annotation class HomePageType {
    companion object {
        const val NONE = -1
        const val HOME_CODER = 0
        const val HOME_REPO = 1
    }
}