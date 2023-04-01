package com.example.morethanyesterdayv2.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import com.example.morethanyesterdayv2.data.entity.RecordEntity


@Dao
interface RecordDAO {
    @Query("select * from room_record")
    fun getAll(): List<RecordEntity>

    //인서트로 들어온 메모 넘버에 값이 있는데, 읽는값이 충돌되면 업데이트해줌
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recordEntity: RecordEntity)

    @Delete
    fun delete(recordEntity: RecordEntity)

    @Query("SELECT * FROM room_record WHERE exerciseId = :exerciseId")
    fun loadExerciseSetListLiveDataByExerciseId(exerciseId: String): LiveData<List<RecordEntity>>

}