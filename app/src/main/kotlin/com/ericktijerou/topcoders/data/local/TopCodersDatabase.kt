package com.ericktijerou.topcoders.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ericktijerou.topcoders.data.local.dao.CoderDao
import com.ericktijerou.topcoders.data.local.entity.CoderEntity

@Database(entities = [CoderEntity::class], version = 1)
abstract class TopCodersDatabase : RoomDatabase() {

    abstract fun coderDao(): CoderDao

    companion object {
        private const val DB_NAME = "topcoders_database"

        fun newInstance(context: Context): TopCodersDatabase {
            return Room.databaseBuilder(context, TopCodersDatabase::class.java, DB_NAME)
                .build()
        }
    }
}