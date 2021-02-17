package com.ericktijerou.topcoders.ui.mapper

import com.ericktijerou.topcoders.core.EMPTY
import com.ericktijerou.topcoders.domain.entity.Repo
import com.ericktijerou.topcoders.ui.entity.RepoView
import com.ericktijerou.topcoders.ui.util.toRelativeTime
import java.util.*

fun Repo.toView() = RepoView(
    id = id,
    name = name,
    description = description,
    forkCount = forkCount.toString(),
    stargazerCount = stargazerCount.toString(),
    owner = owner,
    primaryLanguage = primaryLanguage,
    colorLanguage = colorLanguage,
    socialImage = if (!socialImage.contains("avatars")) socialImage else EMPTY,
    updatedAt = updatedAt.toRelativeTime().toLowerCase(Locale.getDefault())
)