package com.ericktijerou.topcoders.data.network

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.ericktijerou.SearchUsersQuery
import com.ericktijerou.topcoders.core.NotFoundException
import com.ericktijerou.topcoders.data.entity.PageInfoModel
import com.ericktijerou.topcoders.data.entity.UserModel
import com.ericktijerou.topcoders.data.local.util.suspendQuery
import com.ericktijerou.topcoders.data.network.util.toData
import javax.inject.Inject

class UserCloudStore @Inject constructor(private val apolloClient: ApolloClient) {

    suspend fun getUserListByLocation(
        cursor: String?,
        pageSize: Int,
        location: String
    ): Pair<PageInfoModel, List<UserModel>> {
        return apolloClient.suspendQuery(
            SearchUsersQuery(pageSize, Input.fromNullable(cursor), "location:$location")
        ).data?.search?.run {
            val results = nodes?.map {
                it?.asUser?.toData()
                    ?: throw NotFoundException()
            } ?: throw NotFoundException()
            PageInfoModel(pageInfo.endCursor, pageInfo.hasNextPage) to results
        } ?: throw NotFoundException()
    }
}