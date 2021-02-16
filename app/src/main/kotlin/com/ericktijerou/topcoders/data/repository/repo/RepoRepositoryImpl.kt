package com.ericktijerou.topcoders.data.repository.repo

import androidx.paging.*
import com.ericktijerou.topcoders.data.local.RepoDataStore
import com.ericktijerou.topcoders.data.local.mapper.toDomain
import com.ericktijerou.topcoders.data.network.RepoCloudStore
import com.ericktijerou.topcoders.domain.entity.Repo
import com.ericktijerou.topcoders.domain.repository.RepoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val local: RepoDataStore,
    private val remote: RepoCloudStore
) : RepoRepository {
    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }

    @ExperimentalPagingApi
    override fun getRepoList(query: String): Flow<PagingData<Repo>> {
        val pagingSourceFactory = { local.getRepositoryList() }
        return Pager(
            config = getDefaultPageConfig(),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = RepoRemoteMediator(
                local,
                remote,
                query
            )
        ).flow.map { pagingData -> pagingData.map { it.toDomain() } }
    }
}