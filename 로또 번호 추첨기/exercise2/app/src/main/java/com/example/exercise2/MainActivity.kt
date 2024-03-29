package com.example.exercise2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import org.w3c.dom.Text
import java.util.*

class MainActivity : AppCompatActivity() {
    private val clearButton: Button by lazy{
        findViewById<Button>(R.id.clearButton)
    }

    private val addButton: Button by lazy{
        findViewById<Button>(R.id.addButton)
    }

    private val runButton: Button by lazy{
        findViewById<Button>(R.id.runButton)
    }

    private val numberPicker: NumberPicker by lazy{
        findViewById<NumberPicker>(R.id.numberPicker)
    }

    private val numberTextViewList: List<TextView> by lazy{
        listOf<TextView>(
            findViewById<TextView>(R.id.textView1),
            findViewById<TextView>(R.id.textView2),
            findViewById<TextView>(R.id.textView3),
            findViewById<TextView>(R.id.textView4),
            findViewById<TextView>(R.id.textView5),
            findViewById<TextView>(R.id.textView6)
        )
    }

    private var didRun = false

    private val pickNumberSet = hashSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker.maxValue = 45
        numberPicker.minValue = 1

        initRunButton()
        initRunAddButton()
        initRunClearButton()
    }
    private fun initRunButton(){
        runButton.setOnClickListener {
            val list = getRandomNumber()

            didRun = true
            list.forEachIndexed { index, i ->
                val textView = numberTextViewList[index]

                textView.text = i.toString()
                textView.isVisible = true

                setNumberBackground(i,textView)

            }

        }
    }

    private fun initRunClearButton(){
        clearButton.setOnClickListener {
            pickNumberSet.clear()
            numberTextViewList.forEach{
                it.isVisible = false
            }
            didRun = false
        }
    }

    private fun initRunAddButton(){
        addButton.setOnClickListener {
            if(didRun){
                Toast.makeText(this,"초기화 후에 사용해주세요",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(pickNumberSet.size>=5){
                Toast.makeText(this,"번호는 5개까지만 선택할 수 있습니다.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(pickNumberSet.contains(numberPicker.value)){
                Toast.makeText(this,"이미 선택한 번호입니다..",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val textView = numberTextViewList[pickNumberSet.size]
            textView.isVisible = true
            textView.text = numberPicker.value.toString()

            setNumberBackground(numberPicker.value,textView)

            pickNumberSet.add(numberPicker.value)

        }
    }



    private fun getRandomNumber(): List<Int>{
        val numberList = mutableListOf<Int>().apply{
            for(i in 1..45){
                if(pickNumberSet.contains(i)) continue
                this.add(i)
            }
        }
        numberList.shuffle()

        val newList = pickNumberSet.toList() + numberList.subList(0,6-pickNumberSet.size)
        return newList.sorted()
    }

    private fun setNumberBackground(i:Int, textView:TextView){
        when(i){
            in 1..10 -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_blue)
            in 11..20 -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_gray)
            in 21..30 -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_green)
            in 31..40 -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_red)
            else -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_yello)
        }
    }
}