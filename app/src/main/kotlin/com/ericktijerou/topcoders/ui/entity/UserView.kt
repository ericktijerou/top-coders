package com.ericktijerou.topcoders.ui.entity

import androidx.annotation.DrawableRes

data class UserView(
    val name: String,
    val username: String,
    val avatarUrl: String,
    val bio: String,
    val info: String,
    @DrawableRes val infoIcon: Int
)
