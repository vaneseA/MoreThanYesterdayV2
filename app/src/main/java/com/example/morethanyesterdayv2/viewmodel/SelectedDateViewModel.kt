package com.example.morethanyesterdayv2.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
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
import com.example.morethanyesterdayv2.dialog.repository
import com.example.morethanyesterdayv2.repository.SelectedDateRepository
import com.example.morethanyesterdayv2.ui.activity.SelectedDateActivity
import kotlinx.coroutines.CoroutineScope

class SelectedDateViewModel(application: Application) : AndroidViewModel(application) {
    private val selectedDateRepository = SelectedDateRepository(application)
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

    // 비동기적으로 DAO를 이용하여 그 날짜와 특정 운동 정보를 가져와(getAll())
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

    fun onDeleteRecord(recordEntity: RecordEntity, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            // 미리 kg값을 받아놓고
            val kg = recordEntity?.kg
            // 일단 삭제
            repository.delete(recordEntity)
            // recordDAO을 이용해 ROOM 안에 있는 MaxKg 값을 가져옴
            val maxKg = withContext(Dispatchers.IO) {
                repository.getMaxKgFromExerciseByExerciseId(recordEntity?.exerciseId ?: "")
            }
            // recordDAO을 이용해 ROOM 안에 있는 MaxKg 값을 가져옴
            val newMaxKg = withContext(Dispatchers.IO) {
                repository.getMaxKgFromRecordByExerciseId(recordEntity?.exerciseId ?: "")
            }

            // maxKg 업데이트
            if (maxKg != null) {
                if (maxKg == kg) {
                    // recordDAO를 이용해 ROOM 안에 있는 RecordEntity 목록을 가져옴
                    repository.getMaxKgFromRecordByExerciseId(recordEntity?.exerciseId ?: "")
                    repository.updateMaxKgByExerciseId(recordEntity?.exerciseId ?: "", newMaxKg)
                }
                } else {
                    if (maxKg == kg) {
                        // recordDAO를 이용해 ROOM 안에 있는 RecordEntity 목록을 가져옴
                        repository.getMaxKgFromRecordByExerciseId(recordEntity?.exerciseId ?: "")
                        repository.updateMaxKgByExerciseId(recordEntity?.exerciseId ?: "", newMaxKg)
                    }

                }
                val intent = Intent(context, SelectedDateActivity::class.java)
                intent.putExtra("selectedDate", recordEntity.selectedDate)
                context.startActivity(intent)

            }
        }
    }
