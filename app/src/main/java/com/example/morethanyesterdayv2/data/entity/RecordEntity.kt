package com.example.morethanyesterdayv2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_record")
class RecordEntity {
    @PrimaryKey(autoGenerate = true)// no에 값이 없을 때 자동증가된 숫자 값을 db에 입력해준다.
    @ColumnInfo(name = "id")
    var setNo: Long? = null


    @ColumnInfo(name = "exerciseId")
    var exerciseId: String = ""

    @ColumnInfo(name = "selectedDate")
    var selectedDate: String = ""

    //  운동이름
    @ColumnInfo(name = "exerciseName")
    var exerciseName: String = ""

    // 운동타입
    @ColumnInfo(name = "exerciseType")
    var exerciseType: String = ""


    // 중량
    @ColumnInfo
    var kg: String = ""

    // 횟수
    @ColumnInfo
    var count: String = ""


    @ColumnInfo(name = "totalSet")
    var totalSet: Int = 0

    @ColumnInfo(name = "totalKG")
    var totalKG: String = ""

    @ColumnInfo(name = "bestKg")
    var bestKg: String = ""

    @ColumnInfo(name = "totalCount")
    var totalCount: String = ""

    constructor(
        exerciseId: String,
        selectedDate: String,
        exerciseName: String,
        exerciseType: String,
        kg: String,
        count: String
    ) {
        this.exerciseId = exerciseId
        this.selectedDate = selectedDate
        this.exerciseName = exerciseName
        this.exerciseType = exerciseType
        this.kg = kg
        this.count = count
    }
}
