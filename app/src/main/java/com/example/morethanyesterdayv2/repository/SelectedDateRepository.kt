package com.example.morethanyesterdayv2.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.morethanyesterdayv2.data.dao.ExerciseDAO
import com.example.morethanyesterdayv2.data.dao.RecordDAO
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import com.example.morethanyesterdayv2.data.entity.RecordEntity
import com.example.morethanyesterdayv2.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SelectedDateRepository(
    application: Application
) {

    private val exerciseDAO: ExerciseDAO = AppDatabase.getDatabase(application).exerciseDAO()
    private val recordDAO: RecordDAO = AppDatabase.getDatabase(application).recordDAO()

    fun getExerciseSetListLiveDataById(exerciseId: String): LiveData<List<RecordEntity>> {
        return recordDAO.loadExerciseSetListLiveDataByExerciseId(exerciseId)
    }

    suspend fun getAll(): List<ExerciseEntity> {
        return withContext(Dispatchers.IO) {
            exerciseDAO.getAll()
        }
    }

    suspend fun getAllByDate(selectedDate: String): List<ExerciseEntity> {
        return withContext(Dispatchers.IO) {
            exerciseDAO.getAllByDate(selectedDate)
        }
    }
}