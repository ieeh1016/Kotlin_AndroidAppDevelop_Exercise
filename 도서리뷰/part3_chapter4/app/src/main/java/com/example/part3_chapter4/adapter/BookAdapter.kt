package com.example.part3_chapter4.adapter

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.part3_chapter4.model.Book

class BookAdapter: ListAdapter<Book, BookAdapter.BookItemViewHolder>(diffiUtil) {

    inner class BookItemViewHolder(private val binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root){

    }
}