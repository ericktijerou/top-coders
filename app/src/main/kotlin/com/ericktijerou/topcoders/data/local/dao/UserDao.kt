package com.ericktijerou.topcoders.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ericktijerou.topcoders.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAll(): PagingSource<Int, UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(vararg users: UserEntity)

    @Query("DELETE FROM User")
    suspend fun clearAll()
}
