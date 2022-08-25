package com.example.exercise3

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity:AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())

    private val dairyEditText: EditText by lazy{
        findViewById<EditText>(R.id.dairyEditText)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        val detailPreferences = getSharedPreferences("dairy", Context.MODE_PRIVATE)

        dairyEditText.setText(detailPreferences.getString("detail","공 란"))

        val runnable = Runnable {
            getSharedPreferences("dairy", Context.MODE_PRIVATE).edit {
                putString("detail",dairyEditText.text.toString())
            }
        }
        dairyEditText.addTextChangedListener {
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable,500)
        }
    }
}