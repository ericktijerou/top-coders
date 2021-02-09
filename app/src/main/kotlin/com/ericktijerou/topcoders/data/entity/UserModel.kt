package com.ericktijerou.topcoders.data.entity

import com.ericktijerou.topcoders.data.local.entity.UserEntity
import com.ericktijerou.topcoders.domain.entity.User

data class UserModel(
    val name: String,
    val username: String,
    val avatarUrl: String,
    val bio: String,
    val company: String,
    val createdAt: String
)