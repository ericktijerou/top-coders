package com.ericktijerou.topcoders.data.local.utils

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.toFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
fun <D : Operation.Data, T, V : Operation.Variables> ApolloClient.suspendQuery(query: Query<D, T, V>): Flow<Response<T>> =
    query(query).toFlow()