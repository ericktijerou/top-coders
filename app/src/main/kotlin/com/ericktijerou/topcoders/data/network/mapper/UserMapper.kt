package com.ericktijerou.topcoders.data.network.mapper

import com.ericktijerou.SearchUsersQuery
import com.ericktijerou.topcoders.data.entity.UserModel
import com.ericktijerou.topcoders.data.network.util.asString

fun SearchUsersQuery.AsUser.toData() = UserModel(
    name = name.orEmpty(),
    username = login,
    avatarUrl = avatarUrl.asString(),
    bio = bio.orEmpty().trim(),
    company = company.orEmpty(),
    createdAt = createdAt.asString()
)

