package com.example.viewmodellivedatatest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodellivedatatest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!

    lateinit var myNumberViewModel: MyNumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myNumberViewModel = ViewModelProvider(this).get(MyNumberViewModel::class.java)

        // 뷰모델이 가지고 있는 값의 변경사항을 관찰할 수 있는 라이브 데이터를 옵저빙한다.
        myNumberViewModel.currentValue.observe(this, Observer {
            binding.numberTextview.text = it.toString()
        })
        binding.plusBtn.setOnClickListener(this)
        binding.minusBtn.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val userInput = binding.numberInputEdittext.text.toString().toInt()
        // 뮤모델에 라이브 데이터 값을 변경하는 메소드 실행
        when(v){
            binding.plusBtn ->
                myNumberViewModel.updateValue(actionType = ActionType.PLUS,userInput)
            binding.minusBtn ->
                myNumberViewModel.updateValue(actionType = ActionType.MINUS,userInput)
        }
    }
}