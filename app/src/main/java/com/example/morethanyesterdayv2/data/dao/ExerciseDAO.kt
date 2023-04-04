package com.example.morethanyesterdayv2.data.dao

import androidx.room.*
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import com.example.morethanyesterdayv2.data.entity.RecordEntity


//Dao는 Room에서 데이터베이스와 상호작용을 위한 인터페이스이다,entity와 매핑되는 테이블에 대한 CRUD 작업을 수행한다.
@Dao
interface ExerciseDAO {
    @Query("select * from room_exercise")
    fun getAll(): List<ExerciseEntity>


    //인서트로 들어온 메모 넘버에 값이 있는데, 읽는값이 충돌되면 업데이트해줌
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exerciseEntity: ExerciseEntity)

    @Update
    fun update(exerciseEntity: ExerciseEntity)

    @Delete
    fun delete(exerciseEntity: ExerciseEntity)

    @Query("SELECT * FROM room_exercise WHERE selectedDate=:selectedDate ORDER BY id ASC")
    fun getAllByDate(selectedDate: String): List<ExerciseEntity>

    //ROOM에서 totalCount를 추출하는 쿼리
    @Query("SELECT totalCount FROM room_record WHERE exerciseId = :exerciseId")
    fun getTotalCountById(exerciseId: String?): Int?

    //ROOM에서 maxKg를 업데이트하는 쿼리
    @Query("UPDATE room_exercise SET maxKg = :maxKg WHERE exerciseId = :exerciseId")
    fun updateMaxKgByExerciseId(exerciseId: String, maxKg: Double)

    //ROOM에서 totalSet를 업데이트하는 쿼리
    @Query("UPDATE room_exercise SET totalSet = :totalSet WHERE exerciseId = :exerciseId")
    fun updateTotalSetByExerciseId(exerciseId: String, totalSet: Int)

    //ROOM에서 totalCount 업데이트하는 쿼리
    @Query("UPDATE room_exercise SET totalCount = :totalCount WHERE exerciseId = :exerciseId")
    fun updateTotalCountByExerciseId(exerciseId: String, totalCount: Int)

    //ROOM에서 totalKg 업데이트하는 쿼리
    @Query("UPDATE room_exercise SET totalKg = :totalKg WHERE exerciseId = :exerciseId")
    fun updateTotalKgByExerciseId(exerciseId: String, totalKg: Double)

    //"room_exercise" 테이블에서 "exerciseId" 필드가 입력받은 "exerciseId" 값과 일치하는 레코드를 삭제하는 쿼리
    @Query("DELETE FROM room_exercise WHERE exerciseId = :exerciseId")
    fun deleteExerciseByExerciseId(exerciseId: String)


}

