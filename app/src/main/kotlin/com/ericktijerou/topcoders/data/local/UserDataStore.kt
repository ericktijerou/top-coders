package com.ericktijerou.topcoders.data.local

import com.ericktijerou.topcoders.data.entity.UserModel
import com.ericktijerou.topcoders.data.local.dao.UserDao
import com.ericktijerou.topcoders.data.local.entity.toData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataStore @Inject constructor(private val userDao: UserDao) {
    fun getUserList(): Flow<List<UserModel>> {
        return userDao.getAll().map { it.map { entity -> entity.toData() } }
    }
}