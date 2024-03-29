package com.example.part3_chapter4.model

import com.google.gson.annotations.SerializedName

data class SearchBooksDto (
    @SerializedName("items") val books: List<Book>
    )