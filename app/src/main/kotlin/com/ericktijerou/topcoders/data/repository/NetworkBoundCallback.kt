package com.ericktijerou.topcoders.data.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.*
import retrofit2.Response

abstract class NetworkBoundCallback<RESULT, REQUEST> {

    fun asFlow() = flow<State<RESULT>> {

        // Emit Database content first
        emit(State.Success(fetchFromLocal().first()))

        // Fetch latest posts from remote
        val apiResponse = fetchFromRemote()

        // Parse body
        val remotePosts = apiResponse.body()

        // Check for response validation
        if (apiResponse.isSuccessful && remotePosts != null) {
            // Save posts into the persistence storage
            saveRemoteData(remotePosts)
        } else {
            // Something went wrong! Emit Error state.
            emit(State.Failed(apiResponse.message()))
        }

        // Retrieve posts from persistence storage and emit
        emitAll(
            fetchFromLocal().map {
                State.Success<RESULT>(it)
            }
        )
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
