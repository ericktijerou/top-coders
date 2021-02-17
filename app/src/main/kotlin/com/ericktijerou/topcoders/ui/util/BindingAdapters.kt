package com.ericktijerou.topcoders.ui.util

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import coil.load
import com.ericktijerou.topcoders.R
import com.ericktijerou.topcoders.core.NUMBER_ZERO

object BindingAdapters {
    @BindingAdapter("app:loadUrl")
    @JvmStatic
    fun loadUrl(view: ImageView, url: String) {
        view.visible(url.isNotEmpty())
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
            view.context.getAttributeColor(R.attr.textColorOption)
        }
        view.setBackgroundColor(colorInt)
    }

    @BindingAdapter("app:text")
    @JvmStatic
    fun text(view: TextView, value: String) {
        view.visible(value.isNotEmpty())
        view.text = value
    }

    @BindingAdapter("app:primaryLanguage")
    @JvmStatic
    fun primaryLanguage(view: TextView, value: String) {
        val text = if (value.isNotEmpty()) value else view.context.getString(R.string.label_undefined)
        view.text = text
    }
}