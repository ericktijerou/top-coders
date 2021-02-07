package com.ericktijerou.topcoders.data.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.apollographql.apollo.api.Response
import kotlinx.coroutines.flow.*

abstract class NetworkBoundCallback<RESULT, REQUEST> {

    fun asFlow() = flow<State<RESULT>> {
        emit(State.Success(fetchFromLocal().first()))
        val apiResponse = fetchFromRemote()
        val remoteData = apiResponse.data
        if (!apiResponse.hasErrors() && remoteData != null) {
            saveRemoteData(remoteData)
        } else {
            emit(State.Failed(apiResponse.errors?.first()?.message.orEmpty()))
        }
        emitAll(fetchFromLocal().map { State.Success<RESULT>(it) })
    }.catch { e ->
        e.printStackTrace()
        emit(State.Failed("Network error!"))
    }

    @WorkerThread
    protected abstract suspend fun saveRemoteData(response: REQUEST)

    @MainThread
    protected abstract fun fetchFromLocal(): Flow<RESULT>

    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<REQUEST>
}
