package com.example.gcf.todoapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    val tag: String = "未分類",

    @PrimaryKey
    val content: String
)