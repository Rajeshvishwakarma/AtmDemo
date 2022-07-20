package com.app.atmapplication.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AtmData::class], version = 1)
abstract class AtmDatabase : RoomDatabase() {
    abstract fun atmData(): AtmDao;

    companion object {
        @Volatile
        private var instance: AtmDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: getAppDatabase(context).also { instance = it }
        }

        fun getAppDatabase(context: Context) = Room.databaseBuilder(
            context,
            AtmDatabase::class.java, "AtmData"
        ).build()
    }
}