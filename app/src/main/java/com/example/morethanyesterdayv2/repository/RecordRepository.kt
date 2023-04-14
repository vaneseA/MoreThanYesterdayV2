package com.example.morethanyesterdayv2.repository

import com.example.morethanyesterdayv2.data.dao.ExerciseDAO
import com.example.morethanyesterdayv2.data.dao.RecordDAO
import com.example.morethanyesterdayv2.data.entity.RecordEntity
import com.example.morethanyesterdayv2.dialog.exerciseDAO

//RecordRepository: RecordEntity에 대한 CRUD 작업을 수행하는 데 사용되는 Repository 클래스
class RecordRepository(private val recordDAO: RecordDAO) {

    //insert(recordEntity: RecordEntity): 주어진 RecordEntity 객체를 데이터베이스에 삽입
    fun insert(recordEntity: RecordEntity) {
        recordDAO.insert(recordEntity)
    }


    //delete(recordEntity: RecordEntity): 주어진 RecordEntity 객체를 데이터베이스에서 삭제
    fun delete(recordEntity: RecordEntity) {
        recordDAO.delete(recordEntity)
    }

    //deleteRecordByExerciseId : exerciseId에 해당하는 exercise의 모든 record를 삭제
    fun deleteRecordByExerciseId(exerciseId: String) {
        recordDAO.deleteRecordByExerciseId(exerciseId)
    }

    //getRecordCountFromRecordByExerciseId(exerciseId: String?): Int: RecordEntity 테이블에서 주어진 exerciseId와 관련된 레코드의 개수를 반환
    fun getRecordCountFromRecordByExerciseId(exerciseId: String?): Int {
        return recordDAO.getCountSetFromRecordByExerciseId(exerciseId)
    }

    //getTotalCountFromRecordByExerciseId(exerciseId: String?): Int: RecordEntity 테이블에서 주어진 exerciseId와 관련된 모든 레코드의 총 개수를 반환
    fun getTotalCountFromRecordByExerciseId(exerciseId: String?): Int {
        return recordDAO.getTotalCountFromRecordByExerciseId(exerciseId)
    }

    //getCountSetFromRecordByExerciseId(exerciseId: String?): Int: RecordEntity 테이블에서 주어진 exerciseId와 관련된 모든 레코드에서 총 세트 수를 반환
    fun getCountSetFromRecordByExerciseId(exerciseId: String?): Int {
        return recordDAO.getCountSetFromRecordByExerciseId(exerciseId)
    }


    //getMaxKgFromRecordByExerciseId : exerciseId에 해당하는 exercise에서 기록된 최대 중량 값을 반환
    fun getMaxKgFromRecordByExerciseId(exerciseId: String): Double {
        return recordDAO.getMaxKgByExerciseId(exerciseId)
    }


}
