package com.example.morethanyesterdayv2.AboutRoom

import androidx.room.Database
import androidx.room.RoomDatabase

//룸데이터를 추가할때 필요한클래스
@Database(entities = arrayOf(RoomMemo::class), version = 1, exportSchema = false)
abstract class RoomHelper : RoomDatabase() {
    abstract fun roomMemoDao(): RoomMemoDAO
}