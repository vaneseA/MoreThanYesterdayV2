package com.example.morethanyesterdayv2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.morethanyesterdayv2.db.AppDatabase
import com.example.morethanyesterdayv2.data.dao.ExerciseDAO
import com.example.morethanyesterdayv2.data.entity.ExerciseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.morethanyesterdayv2.data.dao.RecordDAO
import com.example.morethanyesterdayv2.data.entity.RecordEntity

class SelectedDateViewModel(application: Application) : AndroidViewModel(application) {
    private val exerciseDAO: ExerciseDAO = AppDatabase.getDatabase(application).exerciseDAO()
    private val recordDAO: RecordDAO = AppDatabase.getDatabase(application).recordDAO()
    private val exerciseList = MutableLiveData<List<ExerciseEntity>>()
    private val recordList = MutableLiveData<List<RecordEntity>>()
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

    fun loadExerciseSetListByDateAndName(selectedDate: String, exerciseName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = recordDAO.getRecordsBySelectedDateAndExerciseName(selectedDate, exerciseName)
            withContext(Dispatchers.Main) {
                recordList.value = list
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

    fun deleteExercise(entity: ExerciseEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            exerciseDAO.delete(entity)
            loadExerciseList()
        }
    }

    fun updateExercise(entity: ExerciseEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            exerciseDAO.update(entity)
            loadExerciseList()
        }
    }

    fun insertExercise(entity: ExerciseEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            exerciseDAO.insert(entity)
            loadExerciseList()
        }
    }


}