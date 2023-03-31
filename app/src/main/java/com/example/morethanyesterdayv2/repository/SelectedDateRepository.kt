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

class SelectedDateRepository(application: Application) {
    private val exerciseDAO: ExerciseDAO = AppDatabase.getDatabase(application).exerciseDAO()
    private val recordDAO: RecordDAO = AppDatabase.getDatabase(application).recordDAO()

//    fun getExerciseListByDate(selectedDate: String): LiveData<List<ExerciseEntity>> {
//        return exerciseDAO.getExerciseListByDate(selectedDate)
//    }


    // DAO를 이용하여 선택한 날짜에 해당하는 특정 운동 정보를 가져오는 메서드
    suspend fun getExerciseSetListByDateAndName(
        selectedDate: String,
        exerciseName: String
    ): List<RecordEntity> {
        return withContext(Dispatchers.IO) {
            recordDAO.getRecordsBySelectedDateAndExerciseName(selectedDate, exerciseName)
        }
    }


//    suspend fun insertExercise(exercise: ExerciseEntity) {
//        exerciseDAO.insertExercise(exercise)
//    }
//
//    suspend fun deleteExercise(exercise: ExerciseEntity) {
//        exerciseDAO.deleteExercise(exercise)
//    }
//
//    suspend fun updateExercise(exercise: ExerciseEntity) {
//        exerciseDAO.updateExercise(exercise)
//    }
//
//    suspend fun insertRecord(record: RecordEntity) {
//        exerciseDAO.insertRecord(record)
//    }
//
//    suspend fun deleteRecord(record: RecordEntity) {
//        exerciseDAO.deleteRecord(record)
//    }
//
//    suspend fun updateRecord(record: RecordEntity) {
//        exerciseDAO.updateRecord(record)
//    }

}