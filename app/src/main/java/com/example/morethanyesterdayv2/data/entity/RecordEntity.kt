package com.example.morethanyesterdayv2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_record")
class RecordEntity {
    @PrimaryKey(autoGenerate = true)// no에 값이 없을 때 자동증가된 숫자 값을 db에 입력해준다.
    @ColumnInfo(name = "id")
    var id: Long? = null


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
    var kg: Double = 0.0

    // 횟수
    @ColumnInfo
    var count: Int = 0


    @ColumnInfo(name = "totalSet")
    var totalSet: Int = 0

    @ColumnInfo(name = "totalKG")
    var totalKG: Int = 0

    @ColumnInfo(name = "maxKg")
    var maxKg: Double = 0.0

    @ColumnInfo(name = "totalCount")
    var totalCount: Int = 0

    constructor(
        exerciseId: String,
        selectedDate: String,
        exerciseName: String,
        exerciseType: String,
        kg: Double,
        count: Int,
        totalCount: Int,
        maxKg: Double
    ) {
        this.exerciseId = exerciseId
        this.selectedDate = selectedDate
        this.exerciseName = exerciseName
        this.exerciseType = exerciseType
        this.kg = kg
        this.count = count
        this.totalCount = totalCount
        this.maxKg = maxKg
    }
}
