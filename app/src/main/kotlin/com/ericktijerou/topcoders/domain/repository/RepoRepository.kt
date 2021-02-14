package com.ericktijerou.topcoders.domain.repository

import androidx.paging.PagingData
import com.ericktijerou.topcoders.domain.entity.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    fun getRepoListByLocation(location: String): Flow<PagingData<Repo>>
}