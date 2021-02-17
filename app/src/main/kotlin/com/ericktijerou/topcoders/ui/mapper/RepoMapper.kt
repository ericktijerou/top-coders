package com.ericktijerou.topcoders.ui.mapper

import com.ericktijerou.topcoders.domain.entity.Repo
import com.ericktijerou.topcoders.ui.entity.RepoView

fun Repo.toView() = RepoView(
    id = id,
    name = name,
    description = description,
    forkCount = forkCount.toString(),
    stargazerCount = stargazerCount.toString(),
    owner = owner,
    primaryLanguage = primaryLanguage,
    colorLanguage = colorLanguage
)