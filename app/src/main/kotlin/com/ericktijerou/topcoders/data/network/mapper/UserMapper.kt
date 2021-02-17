package com.ericktijerou.topcoders.data.network.mapper

import com.ericktijerou.SearchTotalStarsQuery
import com.ericktijerou.SearchUsersQuery
import com.ericktijerou.topcoders.data.entity.LanguageModel
import com.ericktijerou.topcoders.data.entity.UserModel
import com.ericktijerou.topcoders.data.network.util.asString

fun SearchUsersQuery.AsUser.toData() = UserModel(
    id = id,
    name = name.orEmpty(),
    username = login,
    avatarUrl = avatarUrl.asString(),
    bio = bio.orEmpty().trim(),
    company = company.orEmpty(),
    createdAt = createdAt.asString(),
    url = url.asString(),
    followersCount = followers.totalCount
)

fun SearchUsersQuery.PrimaryLanguage.toData() = LanguageModel(
    name = name,
    color = color.orEmpty()
)

