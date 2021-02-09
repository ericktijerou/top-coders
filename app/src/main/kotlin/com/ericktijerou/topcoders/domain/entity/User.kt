package com.ericktijerou.topcoders.domain.entity

data class User(
    val name: String,
    val username: String,
    val avatarUrl: String,
    val bio: String,
    val company: String,
    val createdAt: String
)