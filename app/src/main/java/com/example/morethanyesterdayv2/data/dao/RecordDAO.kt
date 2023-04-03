package com.example.morethanyesterdayv2.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
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

    //ROOM에서 exerciseId로 된 값이 몇 개인지 카운트하는 쿼리
    @Query("SELECT COUNT(*) FROM room_record WHERE id = :exerciseId")
    fun getRecordCountByExerciseId(exerciseId: String?): Int

    //ROOM에서 exerciseId로 된 제일 높은 totalCount를 얻는 쿼리
    @Query("SELECT MAX(totalCount) FROM room_record WHERE id = :exerciseId")
    fun getTotalCountByExerciseId(exerciseId: String?): Int

    //ROOM에서 kg중 maxKg를 추출하는 쿼리
    @Query("SELECT MAX(kg) FROM room_record WHERE exerciseId = :exerciseId")
    fun getMaxKgByExerciseId(exerciseId: String): Int?


    //ROOM에서 maxKg를 업데이트하는 쿼리
    @Query("UPDATE room_record SET maxKg = :maxKg WHERE exerciseId = :exerciseId")
    fun updateMaxKgByExerciseId(exerciseId: String, maxKg: Double)

}