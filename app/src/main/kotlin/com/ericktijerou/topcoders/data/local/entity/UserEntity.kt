package com.ericktijerou.topcoders.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ericktijerou.topcoders.data.entity.UserModel
import com.ericktijerou.topcoders.domain.entity.User

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    val name: String,
    val username: String,
    val avatarUrl: String,
    val bio: String
)

fun UserEntity.toDomain() = User(
    name = name,
    username = username,
    avatarUrl = avatarUrl,
    bio = bio
)