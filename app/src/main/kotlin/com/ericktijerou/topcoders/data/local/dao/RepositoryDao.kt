package com.ericktijerou.topcoders.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ericktijerou.topcoders.data.local.entity.RepoEntity

@Dao
interface RepositoryDao {
    @Query("SELECT * FROM Repository")
    fun getAll(): PagingSource<Int, RepoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositories(vararg repos: RepoEntity)

    @Query("DELETE FROM Repository")
    suspend fun clearAll()
}
