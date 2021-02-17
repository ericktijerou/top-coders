package com.ericktijerou.topcoders.data.network.mapper

import com.ericktijerou.SearchRepositoriesQuery
import com.ericktijerou.topcoders.data.entity.RepoModel

fun SearchRepositoriesQuery.AsRepository.toData(): RepoModel {
    return RepoModel(
        id = id,
        name = name,
        description = description.orEmpty(),
        forkCount = forks.totalCount,
        stargazerCount = stargazers.totalCount,
        owner = owner.login,
        primaryLanguage = primaryLanguage?.name.orEmpty(),
        colorLanguage = primaryLanguage?.color.orEmpty()
    )
}