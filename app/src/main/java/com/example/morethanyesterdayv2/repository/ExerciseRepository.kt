package com.example.morethanyesterdayv2.repository

import android.app.Application
import com.example.morethanyesterdayv2.data.dao.ExerciseDAO
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import com.example.morethanyesterdayv2.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseRepository(application: Application) {
    private val exerciseDAO: ExerciseDAO = AppDatabase.getDatabase(application).exerciseDAO()

    // DAO를 이용하여 모든 운동 정보를 가져오는 메서드
    suspend fun getAll(): List<ExerciseEntity> {
        return withContext(Dispatchers.IO) {
            exerciseDAO.getAll()
        }
    }

    // DAO를 이용하여 선택한 날짜에 해당하는 모든 운동 정보를 가져오는 메서드
    suspend fun getAllByDate(selectedDate: String): List<ExerciseEntity> {
        return withContext(Dispatchers.IO) {
            exerciseDAO.getAllByDate(selectedDate)
        }
    }
}