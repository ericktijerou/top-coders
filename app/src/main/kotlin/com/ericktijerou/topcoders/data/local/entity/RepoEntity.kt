package com.ericktijerou.topcoders.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Repository")
data class RepoEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val forkCount: Int,
    val stargazerCount: Int,
    val owner: String,
    val primaryLanguage: String,
    val colorLanguage: String,
    val socialImage: String,
    val updatedAt: String
)
