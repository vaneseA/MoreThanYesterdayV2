package com.example.morethanyesterdayv2.repository

import com.example.morethanyesterdayv2.data.dao.ExerciseDAO
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import com.example.morethanyesterdayv2.data.entity.RecordEntity


//RecordRepository: RecordEntity에 대한 CRUD 작업을 수행하는 데 사용되는 Repository
class ExerciseRepository(private val exerciseDAO: ExerciseDAO) {
    //getTotalSetFromExerciseByExerciseId(exerciseId: String?): Int: exerciseId와 관련된 모든 레코드에서 총 세트 수를 반환
    fun getTotalSetFromExerciseByExerciseId(exerciseId: String?): Int {
        return com.example.morethanyesterdayv2.dialog.exerciseDAO.getTotalSetFromExerciseByExerciseId(
            exerciseId
        )
    }

    //getTotalCountFromExerciseByExerciseId : exerciseId에 해당하는 exercise의 총 count 값을 반환
    fun getTotalCountFromExerciseByExerciseId(exerciseId: String?): Int {
        return com.example.morethanyesterdayv2.dialog.exerciseDAO.getTotalCountFromExerciseByExerciseId(
            exerciseId
        )
    }

    //getTotalKgFromExerciseByExerciseId : exerciseId에 해당하는 exercise의 총 중량 값을 반환
    fun getTotalKgFromExerciseByExerciseId(exerciseId: String?): Double {
        return com.example.morethanyesterdayv2.dialog.exerciseDAO.getTotalKgFromExerciseByExerciseId(
            exerciseId
        )
    }

    //getMaxKgFromExerciseByExerciseId : exerciseId에 해당하는 exercise에서 설정된 최대 중량 값을 반환
    fun getMaxKgFromExerciseByExerciseId(exerciseId: String): Double {
        return com.example.morethanyesterdayv2.dialog.exerciseDAO.getMaxKgByExerciseId(exerciseId)
    }

    //updateMaxKgFromExerciseByExerciseId : exerciseId에 해당하는 exercise의 최대 중량 값을 업데이트
    fun updateMaxKgFromExerciseByExerciseId(exerciseId: String, maxKg: Double) {
        exerciseDAO.updateMaxKgByExerciseId(
            exerciseId,
            maxKg
        )
    }

    //deleteExerciseByExerciseId : exerciseId에 해당하는 exercise를 삭제
    fun deleteExerciseByExerciseId(exerciseId: String) {
        exerciseDAO.deleteExerciseByExerciseId(exerciseId)
    }

    //insert(recordEntity: RecordEntity): 주어진 RecordEntity 객체를 데이터베이스에 업데이트
    fun update(exerciseEntity: ExerciseEntity) {
        exerciseDAO.update(exerciseEntity)
    }
}