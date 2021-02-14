package com.ericktijerou.topcoders.domain.entity

data class Repo(
    val id: String,
    val name: String,
    val description: String,
    val forkCount: Int,
    val stargazerCount: Int,
    val owner: String,
    val primaryLanguage: String,
    val colorLanguage: String
)
