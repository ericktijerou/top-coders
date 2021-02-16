package com.ericktijerou.topcoders.domain.repository

import androidx.paging.PagingData
import com.ericktijerou.topcoders.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserList(query: String): Flow<PagingData<User>>
}