package com.example.part3_chapter4.api

import com.example.part3_chapter4.model.SearchBooksDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BookService {
    @GET("/v1/search/book_json")
    fun getBookByName(
        @Header("X-Naver-Client-Id") id: String,
        @Header("X-Naver-Client-Secret") secretKey: String,
        @Query("query") keyword: String
    ): Call<SearchBooksDto>
}