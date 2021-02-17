package com.ericktijerou.topcoders.ui.entity

data class RepoView(
    val id: String,
    val name: String,
    val description: String,
    val forkCount: String,
    val stargazerCount: String,
    val owner: String,
    val primaryLanguage: String,
    val colorLanguage: String
)