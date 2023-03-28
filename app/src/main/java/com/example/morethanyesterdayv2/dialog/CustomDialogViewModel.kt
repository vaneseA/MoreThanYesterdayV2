package com.example.morethanyesterdayv2.dialog
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ActionType {
    COUNTPLUS,COUNTMINUS,WEIGHTPLUS,WEIGHTMINUS

}
//데이터의 변경
// 뷰모델은 데이터의 변경사항을 알려주는 라이브 데이터를 가지고 있고

class CustomDialogViewModel : ViewModel() {

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
    init {
        Log.d(TAG, "MynumberViewModel - 생성자 호출 ")
        _currentWeightValue.value = 0
        _currentCountValue.value = 0
    }

    fun updateValue(actionType: ActionType, input: Int){
        when(actionType){
            ActionType.WEIGHTPLUS ->
                _currentWeightValue.value = _currentWeightValue.value?.plus(input)
            ActionType.WEIGHTMINUS ->
                _currentWeightValue.value = _currentWeightValue.value?.minus(input)
            ActionType.COUNTPLUS ->
                _currentCountValue.value = _currentCountValue.value?.plus(input)
            ActionType.COUNTMINUS ->
                _currentCountValue.value = _currentCountValue.value?.minus(input)

        }
    }

}

class MainViewModel : ViewModel() {
    var selectedDate: String = ""
    var userID: String = "userID"
    var fname: String = ""
    var str: String = ""
    // ... 추가적으로 ViewModel에서 유지할 변수 및 함수를 선언합니다.
}