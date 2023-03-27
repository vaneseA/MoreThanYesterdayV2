package com.example.morethanyesterdayv2.aboutrvinrv

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomConverters {
    @TypeConverter
    fun fromString(value: String?): List<RecordEntity> {
        val listType = object : TypeToken<List<RecordEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<RecordEntity>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}