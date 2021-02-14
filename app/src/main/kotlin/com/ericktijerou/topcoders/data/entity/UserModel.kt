package com.ericktijerou.topcoders.data.entity

data class UserModel(
    val id: String,
    val name: String,
    val username: String,
    val avatarUrl: String,
    val bio: String,
    val company: String,
    val createdAt: String
)