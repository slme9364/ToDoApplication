package com.example.gcf.todoapplication.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gcf.todoapplication.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDAO(): TaskDAO
}