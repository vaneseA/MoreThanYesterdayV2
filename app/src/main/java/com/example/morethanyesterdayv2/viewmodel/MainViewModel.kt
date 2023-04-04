package com.example.morethanyesterdayv2.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import com.example.morethanyesterdayv2.repository.SelectedDateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val selectedDateRepository = SelectedDateRepository(application)
    private val exerciseList = MutableLiveData<List<ExerciseEntity>>()
    var selectedDate: String = ""
    var fname: String = ""

    fun getExerciseList(): LiveData<List<ExerciseEntity>> {
        if (exerciseList.value == null) {
            loadExerciseList()
        }
        return exerciseList
    }

    // 비동기적으로 DAO를 이용하여 그 날짜의 운동 정보를 가져와(getAllByDate(selectedDate))
    // coroutine viewModelScope
    // UI에 반영하는 작업을 수행, 이 작업은 백그라운드 스레드에서 실행되며,
    // UI의 반응성과 앱의 안정성을 유지하기 위해 메인 스레드에서 UI를 업데이트.
    fun loadExerciseListByDate(selectedDate: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = selectedDateRepository.getAllByDate(selectedDate)
            withContext(Dispatchers.Main) {
                exerciseList.value = list
            }
        }
    }
    // 비동기적으로 DAO를 이용하여 모든 운동 정보를 가져와(getAll())
    // coroutine viewModelScope
    // UI에 반영하는 작업을 수행, 이 작업은 백그라운드 스레드에서 실행되며,
    // UI의 반응성과 앱의 안정성을 유지하기 위해 메인 스레드에서 UI를 업데이트.
    fun loadExerciseList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = selectedDateRepository.getAll()
            withContext(Dispatchers.Main) {
                exerciseList.value = list
            }
        }
    }

}