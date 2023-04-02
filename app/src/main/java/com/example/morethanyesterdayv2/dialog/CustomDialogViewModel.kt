package com.example.morethanyesterdayv2.dialog
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.morethanyesterdayv2.data.dao.RecordDAO
import com.example.morethanyesterdayv2.db.AppDatabase

enum class ActionType {
    COUNTPLUS,COUNTMINUS,WEIGHTPLUS,WEIGHTMINUS

}
//데이터의 변경
// 뷰모델은 데이터의 변경사항을 알려주는 라이브 데이터를 가지고 있고

class CustomDialogViewModel(application: Application) : AndroidViewModel(application) {
    private val recordDAO: RecordDAO = AppDatabase.getDatabase(application).recordDAO()
    companion object {
        const val TAG: String = "로그"
    }

    //뮤터블(변경 가능) 라이브 데이터 - 수정 가능
    //일반 라이브 데이터 - 값 변동 안됨

    //내부에서도 설정하는 자료형은 뮤터블로
    //변경가능하도록 설정
    private val _currentWeightValue = MutableLiveData<Int>()
    private val _currentCountValue = MutableLiveData<Int>()


    val currentWeightValue: LiveData<Int>
        get() = _currentWeightValue

    val currentCountValue: LiveData<Int>
        get() = _currentCountValue

    //초기값 설정
//    init {
//        _currentWeightValue.value = 0
//        _currentCountValue.value = 0
//    }



}