package com.example.viewmodellivedatatest


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


enum class ActionType{
    PLUS, MINUS
}
// 데이터의 변경
// 뷰모델은 데이터의 변경사항을 알려주는 라이브 데이터를 가지고 있고
class MyNumberViewModel : ViewModel(){

    companion object{
        const val TAG: String = "로그"
    }
    // 뮤터블 라이브 데이터 - 수정 가능한 녀석
    // 라이브 데이터 - 값 변동 안됨

    // 내부에서 설정하는 자료형은 뮤터블로
    // 변경가능하도록 설정
    private val _currentValue = MutableLiveData<Int>()

    val currentValue: LiveData<Int>
        get() = _currentValue
    // 뷰모델이 생성될대 초기값 설정
    init{
        Log.d(TAG,"뷰모델 생성자 호출")
        //라이브 데이터로 맵핑이 되어있을때 값을 수정하려면 value를 이용한다. 일반 라이브 데이타는 변경이 안된다 뮤터블이기때문에 가능함.
        _currentValue.value = 0
    }

    // 뷰모델이 가지고 있는 값을 변경하는 메소드
    fun updateValue(actionType: ActionType, input: Int){
        when(actionType){
            ActionType.PLUS ->
                _currentValue.value = _currentValue.value?.plus(input)
            ActionType.MINUS ->
                _currentValue.value = _currentValue.value?.minus(input)
        }
    }
}