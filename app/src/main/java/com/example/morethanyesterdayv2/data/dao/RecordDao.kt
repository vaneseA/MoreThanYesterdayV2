package com.example.morethanyesterdayv2.data.dao

import androidx.room.*
import com.example.morethanyesterdayv2.viewmodel.Record
import com.example.morethanyesterdayv2.viewmodel.Routine
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate



@Dao
interface RecordDao {

    @Insert
    suspend fun insertRecord(record: Record)

    @Delete
    suspend fun deleteRecord(record: Record)

    @Update
    suspend fun updateRecord(record: Record)

    @Query("SELECT * FROM records")
    fun getRecordAllDate(): Flow<List<Record>>

    @Query("SELECT * FROM records,routine where recordInRoutineId = routineId")
    fun getRecordInRoutine() :Flow<List<Routine>>





}