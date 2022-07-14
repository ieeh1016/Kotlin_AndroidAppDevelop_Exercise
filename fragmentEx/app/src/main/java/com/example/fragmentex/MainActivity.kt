package com.example.fragmentex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fragmentex.databinding.ActivityMainBinding

class MainActivity:AppCompatActivity() {
    private val fragmentA=FragmentA()


    private var fragmentB=fragmentB()
    private var fragmentC=fragmentC()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayoutA,fragmentA)
        transaction.commit()
    }
    fun openFragmentOnFrameLayoutB(int: Int){
        val transaction = supportFragmentManager.beginTransaction()
        when(int){
            1->transaction.replace(R.id.frameLayoutB,fragmentB)
            2->transaction.replace(R.id.frameLayoutB,fragmentC)
        }
        transaction.commit()
    }
}