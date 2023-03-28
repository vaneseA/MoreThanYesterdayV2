package com.example.morethanyesterdayv2.aboutRoom

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.morethanyesterdayv2.data.dao.ExerciseDAO
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity


//룸데이터를 추가할때 필요한클래스
@Database(entities = arrayOf(ExerciseEntity::class), version = 1, exportSchema = false)
abstract class RoomHelper : RoomDatabase() {
    abstract fun exerciseDAO(): ExerciseDAO

    companion object {
        private lateinit var instance: RoomHelper

        fun getDatabase(application: Application): RoomHelper {
            synchronized(RoomHelper::class.java) {
                if (!::instance.isInitialized) {
                    instance = Room.databaseBuilder(
                        application.applicationContext,
                        RoomHelper::class.java,
                        "room_db"
                    ).build()
                }
            }
            return instance
        }
    }
}