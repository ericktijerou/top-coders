package com.ericktijerou.topcoders.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [], version = 1)
abstract class TopCodersDatabase: RoomDatabase() {
    companion object {
        const val DB_NAME = "topcoders_database"

        fun newInstance(context: Context): TopCodersDatabase {
            return Room.databaseBuilder(context, TopCodersDatabase::class.java, DB_NAME)
                .build()
        }
    }
}