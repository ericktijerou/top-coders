package com.ericktijerou.topcoders.data.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.apollographql.apollo.api.Response
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<RESULT, REQUEST> {

    fun asFlow() = flow<Resource<RESULT>> {
        emit(Resource.Success(fetchFromLocal().first()))
        val apiResponse = fetchFromRemote()
        val remoteData = apiResponse.data
        if (!apiResponse.hasErrors() && remoteData != null) {
            saveRemoteData(remoteData)
        } else {
            emit(Resource.Failed(apiResponse.errors?.first()?.message.orEmpty()))
        }
        emitAll(fetchFromLocal().map { Resource.Success<RESULT>(it) })
    }.catch { e ->
        e.printStackTrace()
        emit(Resource.Failed("Network error!"))
    }

    @WorkerThread
    protected abstract suspend fun saveRemoteData(response: REQUEST)

    @MainThread
    protected abstract fun fetchFromLocal(): Flow<RESULT>

    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<REQUEST>
}
