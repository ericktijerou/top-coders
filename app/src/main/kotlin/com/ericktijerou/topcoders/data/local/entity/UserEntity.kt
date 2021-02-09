package com.ericktijerou.topcoders.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ericktijerou.topcoders.data.entity.UserModel
import com.ericktijerou.topcoders.domain.entity.User

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey var id: Int? = 0,
    val name: String,
    val username: String
)

fun UserEntity.toData() = UserModel(
    name = name,
    username = username
)

fun UserEntity.toDomain() = User(
    name = name,
    username = username
)