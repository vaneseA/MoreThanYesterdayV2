package com.example.morethanyesterdayv2.db

import androidx.room.TypeConverter
import com.example.morethanyesterdayv2.data.entity.RecordEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomConverters {
    // TypeConverter는 Room에서 자동으로 데이터베이스에서 가져온 값을 앱에서 사용하는 데이터 형식으로 변환해주는 역할
    @TypeConverter
    //fromString 함수는 String 값을 List<RecordEntity> 값으로 변환
    fun fromString(value: String?): List<RecordEntity> {
        val listType = object : TypeToken<List<RecordEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    // fromList 함수는 List<RecordEntity> 값을 String 값으로 변환
    fun fromList(list: List<RecordEntity>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}