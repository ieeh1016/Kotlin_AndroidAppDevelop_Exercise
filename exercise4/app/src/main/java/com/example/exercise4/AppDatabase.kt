package com.example.exercise4

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.exercise4.dao.HistoryDao
import com.example.exercise4.model.History


@Database(entities = [History::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun historyDao(): HistoryDao

}