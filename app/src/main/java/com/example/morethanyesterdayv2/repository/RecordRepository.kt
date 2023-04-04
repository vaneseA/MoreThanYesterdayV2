package com.example.morethanyesterdayv2.repository

import androidx.lifecycle.LiveData
import com.example.morethanyesterdayv2.data.dao.RecordDAO
import com.example.morethanyesterdayv2.data.entity.RecordEntity
import com.example.morethanyesterdayv2.dialog.exerciseDAO

class RecordRepository(private val recordDAO: RecordDAO) {

    suspend fun insert(recordEntity: RecordEntity) {
        recordDAO.insert(recordEntity)
    }

    suspend fun delete(recordEntity: RecordEntity) {
        recordDAO.delete(recordEntity)
    }

    fun getRecordCountFromRecordByExerciseId(exerciseId: String?): Int {
        return recordDAO.getCountSetFromRecordByExerciseId(exerciseId)
    }

    fun getTotalSetFromExerciseByExerciseId(exerciseId: String?): Int {
        return exerciseDAO.getTotalSetFromExerciseByExerciseId(exerciseId)
    }

    fun getTotalCountFromRecordByExerciseId(exerciseId: String?): Int {
        return recordDAO.getTotalCountFromRecordByExerciseId(exerciseId)
    }

    fun getTotalCountFromExerciseByExerciseId(exerciseId: String?): Int {
        return exerciseDAO.getTotalCountFromExerciseByExerciseId(exerciseId)
    }
    fun getTotalKgFromExerciseByExerciseId(exerciseId: String?): Double {
        return exerciseDAO.getTotalKgFromExerciseByExerciseId(exerciseId)
    }

    fun getMaxKgFromRecordByExerciseId(exerciseId: String): Double {
        return recordDAO.getMaxKgByExerciseId(exerciseId)
    }
    fun getMaxKgFromExerciseByExerciseId(exerciseId: String): Double {
        return exerciseDAO.getMaxKgByExerciseId(exerciseId)
    }

    suspend fun updateMaxKgFromExerciseByExerciseId(exerciseId: String, maxKg: Double) {
        exerciseDAO.updateMaxKgByExerciseId(exerciseId, maxKg)
    }

    suspend fun deleteRecordByExerciseId(exerciseId: String) {
        recordDAO.deleteRecordByExerciseId(exerciseId)
    }
    suspend fun deleteExerciseByExerciseId(exerciseId: String) {
        exerciseDAO.deleteExerciseByExerciseId(exerciseId)
    }
}
