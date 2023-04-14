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

    //recordDAO를 사용하여 특정 운동의 운동 세트 목록을 가져온다.
    fun getExerciseSetListLiveDataFromRecordById(exerciseId: String): LiveData<List<RecordEntity>> {
        return recordDAO.loadExerciseSetListLiveDataByExerciseId(exerciseId)
    }
    //exerciseDAO를 사용하여 데이터베이스에서 모든 운동 데이터를 가져온다
    suspend fun getAll(): List<ExerciseEntity> {
        return withContext(Dispatchers.IO) {
            exerciseDAO.getAll()
        }
    }
    //exerciseDAO에서 선택된 날짜를 검색하여 데이터베이스에서 해당 날짜의 모든 운동 데이터를 가져온다
    suspend fun getAllByDate(selectedDate: String): List<ExerciseEntity> {
        return withContext(Dispatchers.IO) {
            exerciseDAO.getAllByDate(selectedDate)
        }
    }
}