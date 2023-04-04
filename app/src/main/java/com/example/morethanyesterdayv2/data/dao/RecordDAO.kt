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


    //room_record 테이블에서 exerciseId 필드 값이 exerciseId 매개변수 값과 일치하는 모든 레코드를 가져오는 쿼리
    @Query("SELECT * FROM room_record WHERE exerciseId = :exerciseId")
    fun loadExerciseSetListLiveDataByExerciseId(exerciseId: String): LiveData<List<RecordEntity>>

    //ROOM에서 exerciseId로 된 set값이 총 몇 개인지 카운트하는 쿼리
    @Query("SELECT COUNT(exerciseId) FROM room_record WHERE exerciseId = :exerciseId")
    fun getCountSetFromRecordByExerciseId(exerciseId: String?): Int

    //ROOM에서 exerciseId로 된 목록 count를 모두 더한 값을 얻는 쿼리
    @Query("SELECT SUM(count) FROM room_record WHERE exerciseId = :exerciseId")
    fun getTotalCountFromRecordByExerciseId(exerciseId: String?): Int

    //ROOM에서 exerciseId로 된 목록 kg 모두 더한 값을 얻는 쿼리
    @Query("SELECT SUM(kg) FROM room_record WHERE exerciseId = :exerciseId")
    fun getTotalKgByExerciseId(exerciseId: String?): Double

    //ROOM에서 kg중 maxKg를 추출하는 쿼리
    @Query("SELECT MAX(kg) FROM room_record WHERE exerciseId = :exerciseId")
    fun getMaxKgByExerciseId(exerciseId: String): Double

//    //ROOM에서 maxKg를 업데이트하는 쿼리
//    @Query("UPDATE room_record SET maxKg = :maxKg WHERE exerciseId = :exerciseId")
//    fun updateMaxKgByExerciseId(exerciseId: String, maxKg: Double)

    //"room_exercise" 테이블에서 "exerciseId" 필드가 입력받은 "exerciseId" 값과 일치하는 레코드를 삭제하는 쿼리
    @Query("DELETE FROM room_record WHERE exerciseId = :exerciseId")
    fun deleteRecordByExerciseId(exerciseId: String)
}
