package com.ericktijerou.topcoders.ui.util

import android.graphics.Color
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import coil.load

object BindingAdapters {
    @BindingAdapter("app:loadUrl")
    @JvmStatic
    fun loadUrl(view: ImageView, url: String) {
        view.load(url)
    }

    @BindingAdapter("app:src")
    @JvmStatic
    fun src(view: ImageView, @DrawableRes drawableRes: Int) {
        view.setImageResource(drawableRes)
    }

    @BindingAdapter("app:textColor")
    @JvmStatic
    fun textColor(view: ImageView, color: String) {
        val colorInt = if (color.isNotEmpty()) {
            Color.parseColor(color)
        } else {
            view.context.getAttributeColor(android.R.attr.textColorSecondary)
        }
        view.setBackgroundColor(colorInt)
    }
}