package com.example.gcf.todoapplication.repository

import com.example.gcf.todoapplication.Task

interface TaskRepository {
    fun getAll(): List<Task>
    fun getTaskByContent(content: String): Task?
    fun insert(task: Task)
    fun delete(task: Task)
}