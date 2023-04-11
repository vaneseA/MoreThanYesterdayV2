package com.example.morethanyesterdayv2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.morethanyesterdayv2.data.dao.ExerciseDAO
import com.example.morethanyesterdayv2.data.dao.RecordDAO
import com.example.morethanyesterdayv2.db.AppDatabase


class DialogPasteViewModel(application: Application) : AndroidViewModel(application) {
    var selectedDate: String = ""
    var newSelectedDate: String = ""
    var fname: String = ""
}