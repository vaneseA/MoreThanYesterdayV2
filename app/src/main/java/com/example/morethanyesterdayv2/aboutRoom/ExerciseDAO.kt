package com.example.morethanyesterdayv2.aboutRoom

import androidx.room.*
import com.example.morethanyesterdayv2.aboutRoom.ExerciseEntity
import com.example.morethanyesterdayv2.aboutrvinrv.RecordEntity


//Dao는 Room에서 데이터베이스와 상호작용을 위한 인터페이스이다,entity와 매핑되는 테이블에 대한 CRUD 작업을 수행한다.
@Dao
interface ExerciseDAO {
@Query("select * from room_exercise")
    fun getAll(): List<ExerciseEntity>

    //인서트로 들어온 메모 넘버에 값이 있는데, 읽는값이 충돌되면 업데이트해줌
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exerciseEntity: ExerciseEntity)

    @Delete
    fun delete(exerciseEntity: ExerciseEntity)

    @Query("SELECT * FROM room_exercise WHERE exerciseName = :exerciseName")
    fun getRecordsByExerciseName(exerciseName: String): List<RecordEntity>

}
