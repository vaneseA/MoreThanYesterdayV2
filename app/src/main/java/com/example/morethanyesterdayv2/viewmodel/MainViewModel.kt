package com.example.morethanyesterdayv2.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.morethanyesterdayv2.db.AppDatabase
import com.example.morethanyesterdayv2.data.dao.ExerciseDAO
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import com.example.morethanyesterdayv2.repository.ExerciseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val exerciseRepository = ExerciseRepository(application)
    private val exerciseList = MutableLiveData<List<ExerciseEntity>>()
    var selectedDate: String = ""
    var fname: String = ""

    fun getExerciseList(): LiveData<List<ExerciseEntity>> {
        if (exerciseList.value == null) {
            loadExerciseList()
        }
        return exerciseList
    }

    fun loadExerciseListByDate(selectedDate: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = exerciseRepository.getAllByDate(selectedDate)
            withContext(Dispatchers.Main) {
                exerciseList.value = list
            }
        }
    }

    fun loadExerciseList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = exerciseRepository.getAll()
            withContext(Dispatchers.Main) {
                exerciseList.value = list
            }
        }
    }

}