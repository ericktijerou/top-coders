package com.ericktijerou.topcoders.ui.util

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
}