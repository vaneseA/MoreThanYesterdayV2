package com.example.morethanyesterdayv2.data.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.morethanyesterdayv2.db.RoomConverters


@Entity(tableName = "room_exercise")
@TypeConverters(RoomConverters::class) // TypeConverter 등록
class ExerciseEntity {

    @ColumnInfo
    var selectedDate: String = ""

    @ColumnInfo
    var exerciseName: String = ""

    // id에 값이 없을 때 자동증가된 숫자 값을 db에 입력해준다.
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Long? = null

    @ColumnInfo
    var exerciseId: String = ""

    @ColumnInfo
    var exerciseType: String = ""

    @ColumnInfo(name = "maxKg")
    var maxKg: Double = 0.0

    @ColumnInfo
    var recordList: List<RecordEntity> = emptyList()


    constructor(
        selectedDate: String,
        exerciseName: String,
        exerciseType: String,
        exerciseId: String,
        maxKg: Double
    ) {

        this.selectedDate = selectedDate
        this.exerciseName = exerciseName
        this.exerciseType = exerciseType
        this.exerciseId = exerciseId
        this.maxKg = maxKg
    }
}