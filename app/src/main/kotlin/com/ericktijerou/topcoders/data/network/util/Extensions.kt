package com.ericktijerou.topcoders.data.network.util

import com.ericktijerou.SearchUsersQuery
import com.ericktijerou.topcoders.data.entity.UserModel

fun SearchUsersQuery.AsUser.toData() = UserModel(
    name = name.orEmpty(),
    username = login,
    avatarUrl = (avatarUrl as? String).orEmpty(),
    bio = bio.orEmpty().trim()
)