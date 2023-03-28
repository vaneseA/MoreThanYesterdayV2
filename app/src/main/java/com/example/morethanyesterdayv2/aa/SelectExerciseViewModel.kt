package com.example.morethanyesterdayv2.aa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.morethanyesterdayv2.viewmodel.ExerciseData

class SelectExerciseViewModel : ViewModel() {
    private val _exerciseDataList = MutableLiveData<MutableList<ExerciseData>>()
    val exerciseDataList: LiveData<MutableList<ExerciseData>> = _exerciseDataList

    init {
        _exerciseDataList.value = mutableListOf(
            ExerciseData(name = "데드리프트", type = "등"),
            ExerciseData(name = "풀업", type = "등"),
            ExerciseData(name = "렛풀다운", type = "등"),
            ExerciseData(name = "벤치프레스", type = "가슴"),
            ExerciseData(name = "케이블 시티드 로우", type = "등")
        )
    }

    fun addExerciseData(exerciseData: ExerciseData) {
        _exerciseDataList.value?.add(exerciseData)
        _exerciseDataList.value = _exerciseDataList.value
    }
}