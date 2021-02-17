package com.ericktijerou.topcoders.ui.util

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import coil.load
import com.ericktijerou.topcoders.core.NUMBER_ZERO

object BindingAdapters {
    @BindingAdapter("app:loadUrl")
    @JvmStatic
    fun loadUrl(view: ImageView, url: String) {
        view.load(url)
    }

    @BindingAdapter("app:drawableStart")
    @JvmStatic
    fun drawableStart(view: TextView, @DrawableRes drawableRes: Int) {
        view.setCompoundDrawablesWithIntrinsicBounds(drawableRes, NUMBER_ZERO, NUMBER_ZERO, NUMBER_ZERO)
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

    @BindingAdapter("app:text")
    @JvmStatic
    fun text(view: TextView, value: String) {
        view.visible(value.isNotEmpty())
        view.text = value
    }
}