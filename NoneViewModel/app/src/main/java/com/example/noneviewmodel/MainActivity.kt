package com.example.noneviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import com.example.noneviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!

    private var sum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.numberTextview.setText("0")

        binding.plusBtn.setOnClickListener(this)
        binding.minusBtn.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        val editText: Int = binding.numberInputEdittext.text.toString().toInt()
        when(v){
            binding.minusBtn -> sum = sum - editText
            binding.plusBtn -> sum = sum + editText
        }
        binding.numberTextview.setText(sum.toString())

    }
}