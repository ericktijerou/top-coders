package com.ericktijerou.topcoders.data.mapper

import com.ericktijerou.topcoders.data.entity.UserModel
import com.ericktijerou.topcoders.data.local.entity.UserEntity

fun UserModel.toLocal() = UserEntity(
    name = name,
    username = username,
    avatarUrl = avatarUrl,
    bio = bio,
    company = company,
    createdAt = createdAt
)