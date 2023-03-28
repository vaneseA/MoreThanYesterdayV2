package com.example.morethanyesterdayv2.repository

import com.example.morethanyesterdayv2.data.dao.ExerciseDAO
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity

class ExerciseRepository(private val exerciseDAO: ExerciseDAO) {
    fun getAllExercises(): List<ExerciseEntity> {
        val exercises = exerciseDAO.getAll()
        for (exercise in exercises) {
            val records = exerciseDAO.getRecordsByExerciseName(exercise.exerciseName)
            exercise.recordList = records
        }
        return exercises
    }
}