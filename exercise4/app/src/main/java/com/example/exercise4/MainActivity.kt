package com.example.exercise4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val expressionTextView : TextView by lazy{
        findViewById<TextView>(R.id.expressionTextView)
    }

    private val resultTextView : TextView by lazy{
        findViewById<TextView>(R.id.resultTextView)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun buttonClicked(v: View){
        when(v.id){
            R.id.Button1 -> numberButtonClicked("1")
            R.id.Button2 -> numberButtonClicked("2")
            R.id.Button3 -> numberButtonClicked("3")
            R.id.Button4 -> numberButtonClicked("4")
            R.id.Button5 -> numberButtonClicked("5")
            R.id.Button6 -> numberButtonClicked("6")
            R.id.Button7 -> numberButtonClicked("7")
            R.id.Button8 -> numberButtonClicked("8")
            R.id.Button9 -> numberButtonClicked("9")
            R.id.ButtonPlus -> operatorButtonClicked("+")
            R.id.ButtonMinus -> operatorButtonClicked("-")
            R.id.ButtonMulti -> operatorButtonClicked("*")
            R.id.ButtonDivider -> operatorButtonClicked("/")
            R.id.ButtonMudulo -> operatorButtonClicked("%")
        }
    }

    private fun numberButtonClicked(number:String){
        expressionTextView.text
    }

    private fun operatorButtonClicked(number:String){

    }


    fun resultButtonClicked(v: View){

    }

    fun historyButtonClicked(v: View){

    }

    fun clearButtonClicked(v: View){

    }
}