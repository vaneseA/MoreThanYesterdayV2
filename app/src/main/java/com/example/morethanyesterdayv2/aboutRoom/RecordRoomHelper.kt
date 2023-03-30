package com.example.morethanyesterdayv2.aboutRoom

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.morethanyesterdayv2.data.entity.RecordEntity
import com.example.morethanyesterdayv2.data.dao.RecordDAO


//룸데이터를 추가할때 필요한클래스
@Database(entities = arrayOf(RecordEntity::class), version = 1, exportSchema = false)
abstract class RecordRoomHelper : RoomDatabase() {
    abstract fun recordDAO(): RecordDAO

    companion object {
        private lateinit var instance: RecordRoomHelper

        fun getDatabase(application: Application): RecordRoomHelper {
            synchronized(RoomHelper::class.java) {
                if (!::instance.isInitialized) {
                    instance = Room.databaseBuilder(
                        application.applicationContext,
                        RecordRoomHelper::class.java,
                        "room_db"
                    ).build()
                }
            }
            return instance
        }
    }
}