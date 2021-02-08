package com.ericktijerou.topcoders.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.ericktijerou.topcoders.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAll(): Flow<List<UserEntity>>
}
