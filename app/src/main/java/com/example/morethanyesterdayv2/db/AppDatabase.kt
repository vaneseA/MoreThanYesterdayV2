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


        //synchronized를 쓴 이유,
        //다른 스레드가 동시에 getDatabase() 메서드를 호출하는 것을 막아주기 때문에, 보다 안정적인 애플리케이션 동작을 보장
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