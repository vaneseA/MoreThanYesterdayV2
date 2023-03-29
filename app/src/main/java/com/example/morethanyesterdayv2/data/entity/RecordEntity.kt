package com.example.morethanyesterdayv2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_record")
class RecordEntity {
    @PrimaryKey(autoGenerate = true)// no에 값이 없을 때 자동증가된 숫자 값을 db에 입력해준다.
    @ColumnInfo(name = "id")
    var setNo: Long? = null

    @ColumnInfo(name = "selectedDate")
    var selectedDate: String = ""

    //  운동이름
    @ColumnInfo(name = "exerciseName")
    var recordName: String = ""

    // 운동타입
    @ColumnInfo(name = "exerciseType")
    var recordType: String = ""


    // 중량
    @ColumnInfo
    var kg: String = ""

    // 횟수
    @ColumnInfo
    var count: String = ""


    constructor(
        selectedDate: String,
        recordName: String,
        recordType: String,
        kg: String,
        count: String
    ) {
        this.selectedDate = selectedDate
        this.recordName = recordName
        this.recordType = recordType
        this.kg = kg
        this.count = count
    }
}
