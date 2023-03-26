package com.example.morethanyesterdayv2.aboutRoom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.morethanyesterdayv2.aboutrvinrv.RecordEntity


@Entity(tableName = "room_exercise")
class ExerciseEntity {
    @PrimaryKey(autoGenerate = true)// no에 값이 없을 때 자동증가된 숫자 값을 db에 입력해준다.
    @ColumnInfo
    var no: Long? = null
    @ColumnInfo
    var exerciseName: String = ""
    @ColumnInfo
    var exerciseType: String = ""

//    var mList : List<RecordEntity>()

    constructor(exerciseName:String,exerciseType:String){
        this.exerciseName = exerciseName
        this.exerciseType = exerciseType
    }
}