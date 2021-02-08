package com.ericktijerou.topcoders.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserListByLocation(location: String): Flow<PagingData<User>>
}