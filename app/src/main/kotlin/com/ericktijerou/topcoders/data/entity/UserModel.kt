package com.ericktijerou.topcoders.data.entity

import com.ericktijerou.topcoders.core.EMPTY

data class UserModel(
    val id: String,
    val name: String,
    val username: String,
    val avatarUrl: String,
    val bio: String,
    val company: String,
    val createdAt: String,
    val url: String,
    val followersCount: Int,
    var stargazerCount: Int = 0,
    var languagePrimary: String = EMPTY,
    var languageColor: String = EMPTY
)