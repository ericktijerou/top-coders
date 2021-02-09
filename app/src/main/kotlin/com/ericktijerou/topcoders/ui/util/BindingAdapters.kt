package com.ericktijerou.topcoders.ui.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import coil.load

object BindingAdapters {
    @BindingAdapter("app:avatarUrl")
    @JvmStatic
    fun avatarUrl(view: ImageView, avatarUrl: String) {
        view.load(avatarUrl)
    }

    @BindingAdapter("app:src")
    @JvmStatic
    fun src(view: ImageView, @DrawableRes drawableRes: Int) {
        view.setImageResource(drawableRes)
    }
}