package com.ericktijerou.topcoders.ui.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

object BindingAdapters {
    @BindingAdapter("app:avatarUrl")
    @JvmStatic
    fun avatarUrl(view: ImageView, avatarUrl: String) {
        view.load(avatarUrl)
    }
}