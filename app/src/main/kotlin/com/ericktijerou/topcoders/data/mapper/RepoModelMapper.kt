package com.ericktijerou.topcoders.data.mapper

import com.ericktijerou.topcoders.data.entity.RepoModel
import com.ericktijerou.topcoders.data.local.entity.RepoEntity

fun RepoModel.toLocal() = RepoEntity(
    id = id,
    name = name,
    description = description,
    forkCount = forkCount,
    stargazerCount = stargazerCount,
    owner = owner,
    primaryLanguage = primaryLanguage,
    colorLanguage = colorLanguage,
    socialImage = socialImage,
    updatedAt = updatedAt
)