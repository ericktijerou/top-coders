package com.ericktijerou.topcoders.data.entity

import com.ericktijerou.topcoders.data.local.entity.UserEntity
import com.ericktijerou.topcoders.domain.User

data class UserModel(val name: String, val username: String)

fun UserModel.toLocal() = UserEntity(
    name = name,
    username = username
)

fun UserModel.toDomain() = User(
    name = name,
    username = username
)