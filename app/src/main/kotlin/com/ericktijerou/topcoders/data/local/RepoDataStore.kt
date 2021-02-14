package com.ericktijerou.topcoders.data.local

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.ericktijerou.topcoders.data.entity.RepoModel
import com.ericktijerou.topcoders.data.local.dao.RepositoryDao
import com.ericktijerou.topcoders.data.local.entity.RepoEntity
import com.ericktijerou.topcoders.data.local.system.PreferencesHelper
import com.ericktijerou.topcoders.data.local.system.TopCodersDatabase
import com.ericktijerou.topcoders.data.mapper.toLocal
import javax.inject.Inject

class RepoDataStore @Inject constructor(
    private val database: TopCodersDatabase,
    private val repositoryDao: RepositoryDao,
    private val preferences: PreferencesHelper
) {
    fun getRepositoryList(): PagingSource<Int, RepoEntity> {
        return repositoryDao.getAll()
    }

    suspend fun saveRepositoryList(list: List<RepoModel>) {
        repositoryDao.insertRepositories(*list.map { it.toLocal() }.toTypedArray())
    }

    suspend fun doOperationInTransaction(method: suspend () -> Unit) {
        database.withTransaction {
            method()
        }
    }

    suspend fun clearRepositories() {
        repositoryDao.clearAll()
    }

    fun getLastRepositoryCursor(): String? = preferences.lastRepositoryCursor

    fun setLastRepositoryCursor(value: String?) {
        preferences.lastRepositoryCursor = value
    }
}