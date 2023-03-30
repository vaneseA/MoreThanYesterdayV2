package com.example.morethanyesterdayv2.repository

//class ExerciseRepository(application: Application) {
//    private val exerciseDAO: ExerciseDAO = RoomHelper.getDatabase(application).exerciseDAO()
//
//    // DAO를 이용하여 모든 운동 정보를 가져오는 메서드
//    suspend fun getAll(): List<ExerciseEntity> {
//        return withContext(Dispatchers.IO) {
//            exerciseDAO.getAll()
//        }
//    }
//
//    // DAO를 이용하여 선택한 날짜에 해당하는 모든 운동 정보를 가져오는 메서드
//    suspend fun getAllByDate(selectedDate: String): List<ExerciseEntity> {
//        return withContext(Dispatchers.IO) {
//            exerciseDAO.getAllByDate(selectedDate)
//        }
//    }
//}