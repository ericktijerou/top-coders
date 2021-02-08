package com.ericktijerou.topcoders.data.local

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.ericktijerou.topcoders.data.entity.UserModel
import com.ericktijerou.topcoders.data.entity.toLocal
import com.ericktijerou.topcoders.data.local.dao.UserDao
import com.ericktijerou.topcoders.data.local.entity.UserEntity
import com.ericktijerou.topcoders.data.local.system.PreferencesHelper
import com.ericktijerou.topcoders.data.local.system.TopCodersDatabase
import javax.inject.Inject

class UserDataStore @Inject constructor(
    private val database: TopCodersDatabase,
    private val userDao: UserDao,
    private val preferences: PreferencesHelper
) {
    fun getUserList(): PagingSource<Int, UserEntity> {
        return userDao.getAll()
    }

    suspend fun saveUserList(list: List<UserModel>) {
        userDao.insertUsers(*list.map { it.toLocal() }.toTypedArray())
    }

    suspend fun doOperationInTransaction(method: suspend () -> Unit) {
        database.withTransaction {
            method()
        }
    }

    suspend fun clearUsers() {
        userDao.clearAll()
    }

    fun getLastUserCursor(): String? = preferences.lastUserCursor

    fun setLastUserCursor(value: String?) {
        preferences.lastUserCursor = value
    }
}