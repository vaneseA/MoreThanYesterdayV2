package com.example.morethanyesterdayv2.aboutrvinrv

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.morethanyesterdayv2.aboutrvinrv.RecordDAO


//룸데이터를 추가할때 필요한클래스
@Database(entities = arrayOf(RecordEntity::class), version = 1, exportSchema = false)
abstract class RecordRoomHelper : RoomDatabase() {
    abstract fun recordDAO(): RecordDAO
}