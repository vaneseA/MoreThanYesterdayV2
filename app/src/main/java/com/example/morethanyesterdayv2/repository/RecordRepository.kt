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

    fun loadExerciseSetListLiveDataByExerciseId(exerciseId: String): LiveData<List<RecordEntity>> {
        return recordDAO.loadExerciseSetListLiveDataByExerciseId(exerciseId)
    }

    fun getRecordCountByExerciseId(exerciseId: String?): Int {
        return recordDAO.getRecordCountByExerciseId(exerciseId)
    }

    fun getTotalCountByExerciseId(exerciseId: String?): Int {
        return recordDAO.getTotalCountByExerciseId(exerciseId)
    }

    fun getTotalKgByExerciseId(exerciseId: String?): Double {
        return recordDAO.getTotalKgByExerciseId(exerciseId)
    }

    fun getMaxKgByExerciseId(exerciseId: String): Double {
        return recordDAO.getMaxKgByExerciseId(exerciseId)
    }

    suspend fun updateMaxKgByExerciseId(exerciseId: String, maxKg: Double) {
        exerciseDAO.updateMaxKgByExerciseId(exerciseId, maxKg)
    }

    suspend fun deleteRecordByExerciseId(exerciseId: String) {
        recordDAO.deleteRecordByExerciseId(exerciseId)
    }
    suspend fun deleteExerciseByExerciseId(exerciseId: String) {
        exerciseDAO.deleteExerciseByExerciseId(exerciseId)
    }
}
