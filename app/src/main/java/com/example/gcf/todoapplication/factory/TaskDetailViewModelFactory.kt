package com.example.gcf.todoapplication.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gcf.todoapplication.repository.TaskRepository
import com.example.gcf.todoapplication.viewmodel.TaskDetailViewModel

class TaskDetailViewModelFactory(private val repo: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == TaskDetailViewModel::class.java)
            return TaskDetailViewModel(repo) as T
        throw IllegalArgumentException("Unknown ViewModel class : ${modelClass.name}")
    }
}