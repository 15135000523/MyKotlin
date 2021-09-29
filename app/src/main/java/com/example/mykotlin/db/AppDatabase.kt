package com.example.mykotlin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private val DATABASE_NAME = "user"
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDb(context: Context): AppDatabase {
            if (instance != null) return instance!!
            else return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .build().apply {
                    instance = this
                }
        }
    }

    abstract fun userDao(): UserDao
}
