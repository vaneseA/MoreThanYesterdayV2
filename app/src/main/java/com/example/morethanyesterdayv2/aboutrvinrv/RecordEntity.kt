package com.example.morethanyesterdayv2.aboutrvinrv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_record")
class RecordEntity {
    @PrimaryKey(autoGenerate = true)// no에 값이 없을 때 자동증가된 숫자 값을 db에 입력해준다.
    @ColumnInfo
    var setNo: Long? = null


    //  운동이름
    @ColumnInfo
    var recordName: String = ""

    // 운동타입
    @ColumnInfo
    var recordType: String = ""


    // 중량
    @ColumnInfo
    var kg: String = ""

    // 횟수
    @ColumnInfo
    var count: String = ""


    constructor(recordName: String, recordType: String, kg: String, count: String) {
        this.recordName = recordName
        this.recordType = recordType
        this.kg = kg
        this.count = count
    }
}
