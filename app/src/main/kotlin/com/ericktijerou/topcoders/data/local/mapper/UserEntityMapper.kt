package com.ericktijerou.topcoders.data.local.mapper

import com.ericktijerou.topcoders.data.local.entity.UserEntity
import com.ericktijerou.topcoders.domain.entity.User

fun UserEntity.toDomain() = User(
    name = name,
    username = username,
    avatarUrl = avatarUrl,
    bio = bio,
    company = company,
    createdAt = createdAt,
    url = url,
    followersCount = followersCount,
    stargazerCount = stargazerCount,
    languagePrimary = languagePrimary,
    languageColor = languageColor
)