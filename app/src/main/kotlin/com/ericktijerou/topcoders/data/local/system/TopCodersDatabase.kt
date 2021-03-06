package com.ericktijerou.topcoders.data.local.system

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ericktijerou.topcoders.data.local.dao.RepositoryDao
import com.ericktijerou.topcoders.data.local.dao.UserDao
import com.ericktijerou.topcoders.data.local.entity.RepoEntity
import com.ericktijerou.topcoders.data.local.entity.UserEntity

@Database(entities = [UserEntity::class, RepoEntity::class], version = 1)
abstract class TopCodersDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun repositoryDao(): RepositoryDao

    companion object {
        private const val DB_NAME = "topcoders_database"

        fun newInstance(context: Context): TopCodersDatabase {
            return Room.databaseBuilder(
                context, TopCodersDatabase::class.java,
                DB_NAME
            ).build()
        }
    }
}