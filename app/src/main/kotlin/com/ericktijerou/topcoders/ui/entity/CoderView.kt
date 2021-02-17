package com.ericktijerou.topcoders.ui.entity

import androidx.annotation.DrawableRes

data class CoderView(
    val name: String,
    val username: String,
    val avatarUrl: String,
    val bio: String,
    val hasCompany: Boolean,
    val info: String,
    @DrawableRes val infoIcon: Int,
    val url: String,
    val followersCount: String,
    var stargazerCount: String,
    var primaryLanguage: String,
    var colorLanguage: String
)
