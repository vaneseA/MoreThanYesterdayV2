package com.example.morethanyesterdayv2.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.morethanyesterdayv2.data.dao.ExerciseDAO
import com.example.morethanyesterdayv2.data.dao.RecordDAO
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import com.example.morethanyesterdayv2.data.entity.RecordEntity

@Database(entities = [ExerciseEntity::class, RecordEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exerciseDAO(): ExerciseDAO
    abstract fun recordDAO(): RecordDAO

    companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(application: Application): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    application.applicationContext,
                    AppDatabase::class.java,
                    "room_db"
                ).build().also { instance = it }
            }
        }
    }
}