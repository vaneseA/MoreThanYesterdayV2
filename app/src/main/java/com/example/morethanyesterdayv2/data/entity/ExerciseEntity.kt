package com.example.morethanyesterdayv2.data.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.morethanyesterdayv2.aboutRoom.RoomConverters


@Entity(tableName = "room_exercise")
@TypeConverters(RoomConverters::class) // TypeConverter 등록
class ExerciseEntity {

    @ColumnInfo
    var selectedDate: String = ""

    @PrimaryKey(autoGenerate = true)// no에 값이 없을 때 자동증가된 숫자 값을 db에 입력해준다.
    @ColumnInfo
    var no: Long? = null

    @ColumnInfo
    var exerciseName: String = ""

    @ColumnInfo
    var exerciseType: String = ""

    @ColumnInfo
    var totalSet: Int = 0

    @ColumnInfo
    var totalKG: String = ""

    @ColumnInfo
    var bestKg: String = ""

    @ColumnInfo
    var totalCount: String = ""

    @ColumnInfo
    var recordList: List<RecordEntity> = emptyList() // recordList 추가


    constructor(exerciseName: String, exerciseType: String) {
        this.exerciseName = exerciseName
        this.exerciseType = exerciseType
    }
}