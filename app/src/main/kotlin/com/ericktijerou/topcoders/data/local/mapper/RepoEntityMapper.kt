package com.ericktijerou.topcoders.data.local.mapper

import com.ericktijerou.topcoders.data.local.entity.RepoEntity
import com.ericktijerou.topcoders.domain.entity.Repo

fun RepoEntity.toDomain() = Repo(
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