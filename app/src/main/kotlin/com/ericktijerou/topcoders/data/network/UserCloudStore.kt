package com.ericktijerou.topcoders.data.network

import com.apollographql.apollo.ApolloClient
import com.ericktijerou.SearchUsersQuery
import com.ericktijerou.topcoders.data.entity.UserModel
import com.ericktijerou.topcoders.data.local.utils.suspendQuery
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class UserCloudStore @Inject constructor(private val apolloClient: ApolloClient) {

    @ExperimentalCoroutinesApi
    suspend fun getUserListByLocation(location: String): Flow<List<UserModel>> {
        return apolloClient.suspendQuery(SearchUsersQuery("location:$location"))
            .transform { it.data }
    }
}