package com.example.morethanyesterdayv2.AboutRoom

import androidx.room.*

@Dao
interface ExerciseDAO {
@Query("select * from room_exercise")
    fun getAll(): List<ExerciseEntity>

    //인서트로 들어온 메모 넘버에 값이 있는데, 읽는값이 충돌되면 업데이트해줌
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exerciseEntity: ExerciseEntity)

    @Delete
    fun delete(exerciseEntity: ExerciseEntity)
}