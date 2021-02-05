package com.ericktijerou.topcoders.utils

import android.view.View

fun View.visible(value: Boolean = true) {
    visibility = if (value) View.VISIBLE else View.GONE
}

fun View.gone() {
    visibility = View.GONE
}