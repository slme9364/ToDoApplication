package com.example.gcf.todoapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gcf.todoapplication.Task
import com.example.gcf.todoapplication.repository.TaskRepository

class CreateTaskViewModel(private val repo: TaskRepository) : ViewModel() {
    fun createTask(task: Task) = repo.insert(task)
}