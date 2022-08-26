package com.example.part3_chapter4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.part3_chapter4.api.BookService
import com.example.part3_chapter4.model.SearchBooksDto
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //retrofit 구현체가 생성이 되서 retrofit이라는 변수에 할당이 된다.
        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val bookService = retrofit.create(BookService::class.java)

        bookService.getBookByName("GBs1ecx1hmePfrG29pdo","9JQH1LYFYQ","안드로이드")
            .enqueue(object : Callback<SearchBooksDto>{
                override fun onResponse(
                    call: Call<SearchBooksDto>,
                    response: Response<SearchBooksDto>
                ) {
                    if(response.isSuccessful.not()){
                        return
                    }

                    response.body()?.books?.forEach{
                        Log.d(TAG, it.toString())
                    }
                }

                override fun onFailure(call: Call<SearchBooksDto>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
    companion object{
        private const val TAG = "MainActivity"
    }
}