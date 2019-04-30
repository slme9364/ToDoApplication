package com.example.gcf.todoapplication.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.gcf.todoapplication.repository.TaskDatabase

class MainViewModel : ViewModel() {
    private lateinit var  database: TaskDatabase

    fun initialize(context: Context) {
        database = Room.databaseBuilder(context, TaskDatabase::class.java, "task.db").allowMainThreadQueries().build()
    }

    fun getTaskDAO() = database.taskDAO()
}