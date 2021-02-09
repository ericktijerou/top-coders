package com.ericktijerou.topcoders.domain.entity

import com.ericktijerou.topcoders.ui.entity.UserView

data class User(
    val name: String,
    val username: String,
    val avatarUrl: String,
    val bio: String,
    val company: String,
    val createdAt: String
)