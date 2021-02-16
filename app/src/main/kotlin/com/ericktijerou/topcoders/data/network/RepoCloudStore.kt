package com.ericktijerou.topcoders.data.network

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.ericktijerou.SearchRepositoriesQuery
import com.ericktijerou.topcoders.core.NotFoundException
import com.ericktijerou.topcoders.data.entity.PageInfoModel
import com.ericktijerou.topcoders.data.entity.RepoModel
import com.ericktijerou.topcoders.data.local.util.suspendQuery
import com.ericktijerou.topcoders.data.network.mapper.toData
import javax.inject.Inject

class RepoCloudStore @Inject constructor(private val apolloClient: ApolloClient) {

    suspend fun getRepoList(
        cursor: String?,
        pageSize: Int,
        query: String
    ): Pair<PageInfoModel, List<RepoModel>> {
        return apolloClient.suspendQuery(
            SearchRepositoriesQuery(pageSize, Input.fromNullable(cursor), query)
        ).data?.search?.run {
            val results = nodes?.map {
                it?.asRepository?.toData()
                    ?: throw NotFoundException()
            } ?: throw NotFoundException()
            PageInfoModel(pageInfo.endCursor, pageInfo.hasNextPage) to results
        } ?: throw NotFoundException()
    }
}