package com.ericktijerou.topcoders.util

import android.view.View

fun View.visible(value: Boolean = true) {
    visibility = if (value) View.VISIBLE else View.GONE
}

fun View.gone() {
    visibility = View.GONE
}