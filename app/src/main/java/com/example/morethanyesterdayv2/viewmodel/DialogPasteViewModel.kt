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
    var fname: String = ""
    //내부에서도 설정하는 자료형은 뮤터블로
    //뮤터블(변경 가능) 라이브 데이터 - 수정 가능
    //일반 라이브 데이터 - 값 변동 안됨
    //변경가능하도록 설정
    private val _currentKg = MutableLiveData<Int>()
    private val _currentLb = MutableLiveData<Int>()
    private val _currentCountValue = MutableLiveData<Int>()


    val currentKg: LiveData<Int>
        get() = _currentKg
    val currentLb: LiveData<Int>
        get() = _currentLb

    val currentCountValue: LiveData<Int>
        get() = _currentCountValue

}