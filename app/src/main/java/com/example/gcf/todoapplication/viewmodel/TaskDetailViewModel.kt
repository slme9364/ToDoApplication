package com.example.gcf.todoapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gcf.todoapplication.Task
import com.example.gcf.todoapplication.repository.TaskRepository

class TaskDetailViewModel(private val repo: TaskRepository) : ViewModel() {
    fun getTask(content: String) = repo.getTaskByContent(content)
    fun delete(task: Task) = repo.delete(task)
}