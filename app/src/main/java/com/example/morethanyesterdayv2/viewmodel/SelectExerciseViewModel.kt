package com.example.morethanyesterdayv2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.morethanyesterdayv2.data.*


class SelectExerciseViewModel(application: Application) : AndroidViewModel(application) {
//    private val repository = ExerciseRepository(application)

    private val _absExerciseDataList = MutableLiveData<MutableList<AbsExerciseData>>()
    private val _backExerciseDataList = MutableLiveData<MutableList<BackExerciseData>>()
    private val _chestExerciseDataList = MutableLiveData<MutableList<ChestExerciseData>>()
    private val _lowerBodyExerciseDataList = MutableLiveData<MutableList<LowerBodyExerciseData>>()
    private val _shoulderExerciseDataList = MutableLiveData<MutableList<ShoulderExerciseData>>()
    private val _trapeziusExerciseDataList = MutableLiveData<MutableList<TrapeziusExerciseData>>()
    private val _tricepsExerciseDataList = MutableLiveData<MutableList<TricepsExerciseData>>()
    private val _customExerciseDataList = MutableLiveData<MutableList<CustomExerciseData>>()

    init {
        _absExerciseDataList.value = mutableListOf(
            AbsExerciseData(name = "크런치", type = "복근"),
            AbsExerciseData(name = "데드버그", type = "복근"),
        )

        _customExerciseDataList.value = mutableListOf(
            CustomExerciseData(name = "커스텀", type = "커스텀")
        )

        _trapeziusExerciseDataList.value = mutableListOf(

        )

        _tricepsExerciseDataList.value = mutableListOf(
            TricepsExerciseData(name = "스컬크러셔", type = "삼두"),
            TricepsExerciseData(name = "로프 푸시다운", type = "삼두"),
            TricepsExerciseData(name = "다이아몬드 푸시업", type = "삼두"),
        )

        _shoulderExerciseDataList.value = mutableListOf(
            ShoulderExerciseData(name = "바벨 숄더 프레스", type = "어깨"),
            ShoulderExerciseData(name = "덤벨 숄더 프레스", type = "어깨"),
            ShoulderExerciseData(name = "사이드 레터럴 레이즈", type = "어깨"),
            ShoulderExerciseData(name = "페이스 풀", type = "어깨"),
            ShoulderExerciseData(name = "숄더 프레스 머신", type = "어깨"),
        )


        _lowerBodyExerciseDataList.value = mutableListOf(
            LowerBodyExerciseData(name = "런지", type = "하체"),
            LowerBodyExerciseData(name = "레그컬", type = "하체"),
            LowerBodyExerciseData(name = "스쿼트", type = "하체"),
            )

        _chestExerciseDataList.value = mutableListOf(
            ChestExerciseData(name = "벤치프레스", type = "가슴"),
            ChestExerciseData(name = "덤벨프레스", type = "가슴"),
        )

        _backExerciseDataList.value = mutableListOf(
            BackExerciseData(name = "데드리프트", type = "등"),
            BackExerciseData(name = "풀업", type = "등"),
            BackExerciseData(name = "렛풀다운", type = "등"),
            BackExerciseData(name = "케이블 시티드 로우", type = "등"),
        )
    }
}