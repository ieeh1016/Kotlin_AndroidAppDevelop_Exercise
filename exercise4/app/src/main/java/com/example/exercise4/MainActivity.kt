package com.example.exercise4

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.room.Room
import com.example.exercise4.model.History
import java.lang.NumberFormatException
import kotlin.math.exp

class MainActivity : AppCompatActivity() {

    private val expressionTextView : TextView by lazy{
        findViewById<TextView>(R.id.expressionTextView)
    }

    private val resultTextView : TextView by lazy{
        findViewById<TextView>(R.id.resultTextView)
    }

    private val historyLayout: View by lazy{
        findViewById(R.id.historyLayout)
    }

    private val historyLinearLayout: View by lazy{
        findViewById(R.id.historyLinearLayout )
    }

    lateinit var db: AppDatabase

    private var isOperator = false
    private var hasOperator = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "historyDB"
        ).build()
    }



    fun buttonClicked(v: View){
        when(v.id){
            R.id.Button0 -> numberButtonClicked("0")
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
            R.id.ButtonMulti -> operatorButtonClicked("x")
            R.id.ButtonDivider -> operatorButtonClicked("/")
            R.id.ButtonMudulo -> operatorButtonClicked("%")
        }
    }

    private fun numberButtonClicked(number:String){

        if(isOperator){
            expressionTextView.append(" ")
        }
        isOperator = false

        val  expressionText = expressionTextView.text.split(" ")
        if(expressionText.isNotEmpty() && expressionText.last().length >=15){
            Toast.makeText(this,"15자리 까지만 사용할 수 있습니다.",Toast.LENGTH_SHORT).show()
            return
        }
        else if(expressionText.last().isEmpty() && number=="0"){
            Toast.makeText(this,"가장 첫번째 숫자에는 0이올수 없습니다.",Toast.LENGTH_SHORT).show()
            return
        }

        expressionTextView.append(number)
        resultTextView.text =calculateExpression()
        //TODO
    }

    private fun operatorButtonClicked(operator:String){
        if(expressionTextView.text.isEmpty()){
            return
        }
        when{
            isOperator -> {
                val text = expressionTextView.text.toString()
                expressionTextView.text = text.dropLast(1) + operator
            }
            hasOperator -> {
                Toast.makeText(this,"연산자는 한 번만 사용할 수 있습니다.",Toast.LENGTH_SHORT).show()
                return
            }
            else -> {
                expressionTextView.append(" $operator")
            }
        }
        val ssb = SpannableStringBuilder(expressionTextView.text)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ssb.setSpan(
                ForegroundColorSpan(getColor(R.color.green)),
                expressionTextView.text.length - 1,
                expressionTextView.text.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        expressionTextView.text = ssb

        isOperator = true
        hasOperator = true
    }


    fun resultButtonClicked(v: View){
        val expressionTexts = expressionTextView.text.split(" " )

        if(expressionTextView.text.isEmpty() || expressionTexts.size == 1){
            return
        }
        if(expressionTexts.size != 3 && hasOperator){
            Toast.makeText(this,"아직 완성되지 않은 수식입니다.",Toast.LENGTH_SHORT).show()
            return
        }
        if(expressionTexts[0].isNumber().not() || expressionTexts[2].isNumber().not()){
            Toast.makeText(this,"오류입니다..",Toast.LENGTH_SHORT).show()
            return
        }

        val expressionText = expressionTextView.text.toString()
        val resultText = calculateExpression()

        Thread(Runnable{
            db.historyDao().insertHistory(History(null,expressionText, resultText ))
        })

        resultTextView.text = ""
        expressionTextView.text = resultText

        isOperator = false
        hasOperator = false
    }

    private fun calculateExpression(): String {
        val expressionText = expressionTextView.text.split(" " )
        if(hasOperator.not() || expressionText.size != 3 ){
            return ""
        }else if(expressionText[0].isNumber().not() || expressionText[2].isNumber().not()){
            return ""
        }
        val exp1 = expressionText[0].toBigInteger()
        val exp2 = expressionText[2].toBigInteger()
        val op = expressionText[1]

        return when(op){
            "+" -> (exp1+exp2).toString()
            "-" -> (exp1-exp2).toString()
            "x" -> (exp1*exp2).toString()
            "/" -> (exp1/exp2).toString()
            "%" -> (exp1%exp2).toString()
            else -> ""
        }
    }

    fun clearButtonClicked(v: View){
        expressionTextView.text = ""
        resultTextView.text = ""
        isOperator = false
        hasOperator = false
    }

    fun historyButtonClicked(v: View){
        historyLayout.isVisible = true
        // TODO 디비에서 기록저장
        // TODO 디비에서 기록가져오기
    }

    fun closeHistoryButtonClicked(v: View){
        historyLayout.isVisible = false
    }

    fun historyClearButtonClicked(v: View){
        //TODO 디비에서 모든 기록 삭제
        //TODO 뷰에서 모든 기록 삭제
    }
}

fun String.isNumber(): Boolean{
    return try {
        this.toBigInteger()
        true
    }catch (e: NumberFormatException){
        false
    }
}