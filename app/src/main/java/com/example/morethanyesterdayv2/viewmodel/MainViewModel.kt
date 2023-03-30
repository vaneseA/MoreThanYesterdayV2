package com.example.morethanyesterdayv2.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.morethanyesterdayv2.db.AppDatabase
import com.example.morethanyesterdayv2.data.dao.ExerciseDAO
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val exerciseDAO: ExerciseDAO = AppDatabase.getDatabase(application).exerciseDAO()
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
            val list = exerciseDAO.getAllByDate(selectedDate)
            withContext(Dispatchers.Main) {
                exerciseList.value = list
            }
        }
    }

    fun loadExerciseList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = exerciseDAO.getAll()
            withContext(Dispatchers.Main) {
                exerciseList.value = list
            }
        }
    }

}