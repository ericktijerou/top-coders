package com.ericktijerou.topcoders.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey val id: String,
    val name: String,
    val username: String,
    val avatarUrl: String,
    val bio: String,
    val company: String,
    val createdAt: String,
    val url: String,
    val followersCount: Int,
    val stargazerCount: Int,
    val languagePrimary: String,
    val languageColor: String
)