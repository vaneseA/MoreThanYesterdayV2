package com.example.morethanyesterdayv2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.morethanyesterdayv2.data.ExerciseData

class SelectExerciseViewModel : ViewModel() {
    private val _exerciseDataList = MutableLiveData<MutableList<ExerciseData>>()

//    init {
//        _exerciseDataList.value = mutableListOf(
//            ExerciseData(name = "데드리프트", type = "등"),
//            ExerciseData(name = "풀업", type = "등"),
//            ExerciseData(name = "렛풀다운", type = "등"),
//            ExerciseData(name = "크런치", type = "복근"),
//            ExerciseData(name = "데드버그", type = "복근"),
//            ExerciseData(name = "벤치프레스", type = "가슴"),
//            ExerciseData(name = "덤벨프레스", type = "가슴"),
//            ExerciseData(name = "케이블 시티드 로우", type = "등")
//
//        )
//
//    }

    fun getExerciseDataList(): LiveData<MutableList<ExerciseData>> {
        return _exerciseDataList
    }
}
