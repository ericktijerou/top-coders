package com.ericktijerou.topcoders.data.repository.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.apollographql.apollo.exception.ApolloHttpException
import com.ericktijerou.topcoders.data.local.RepoDataStore
import com.ericktijerou.topcoders.data.local.entity.RepoEntity
import com.ericktijerou.topcoders.data.network.RepoCloudStore
import java.io.IOException

@ExperimentalPagingApi
class RepoRemoteMediator(
    private val local: RepoDataStore,
    private val remote: RepoCloudStore,
    private val location: String
) : RemoteMediator<Int, RepoEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RepoEntity>
    ): MediatorResult {
        val cursor = when (val pageKeyData = getKeyPageData(loadType)) {
            is MediatorResult.Success -> return pageKeyData
            else -> pageKeyData as? String
        }
        return try {
            val (pageInfo, userList) = remote.getRepoListByLocation(
                cursor,
                state.config.pageSize,
                location
            )
            local.doOperationInTransaction {
                if (loadType == LoadType.REFRESH) local.clearRepositories()
                local.setLastRepositoryCursor(pageInfo.endCursor)
                local.saveRepositoryList(userList)
            }
            MediatorResult.Success(endOfPaginationReached = !pageInfo.hasNextPage)
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: ApolloHttpException) {
            MediatorResult.Error(exception)
        }
    }

    private fun getKeyPageData(loadType: LoadType): Any? {
        return when (loadType) {
            LoadType.REFRESH -> null
            LoadType.PREPEND -> MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> local.getLastRepositoryCursor()
        }
    }
}