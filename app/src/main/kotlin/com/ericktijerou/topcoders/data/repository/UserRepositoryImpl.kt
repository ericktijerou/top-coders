package com.ericktijerou.topcoders.data.repository

import androidx.paging.*
import com.ericktijerou.topcoders.data.local.UserDataStore
import com.ericktijerou.topcoders.data.local.entity.UserEntity
import com.ericktijerou.topcoders.data.local.entity.toData
import com.ericktijerou.topcoders.data.local.entity.toDomain
import com.ericktijerou.topcoders.data.network.UserCloudStore
import com.ericktijerou.topcoders.domain.User
import com.ericktijerou.topcoders.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val local: UserDataStore,
    private val remote: UserCloudStore
): UserRepository {
    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }

    @ExperimentalPagingApi
    override fun getUserListByLocation(location: String): Flow<PagingData<User>> {
        val pagingSourceFactory = { local.getUserList() }
        return Pager(
            config = getDefaultPageConfig(),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = UserRemoteMediator(local, remote, location)
        ).flow.map { pagingData -> pagingData.map { it.toDomain() } }
    }
}