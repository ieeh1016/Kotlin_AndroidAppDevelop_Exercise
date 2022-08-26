package com.example.part3_chapter4.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Book(
    @SerializedName("isbn") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String
)
