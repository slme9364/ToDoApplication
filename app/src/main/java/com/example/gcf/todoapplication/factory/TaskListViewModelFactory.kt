package com.example.gcf.todoapplication.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gcf.todoapplication.repository.TaskRepository
import com.example.gcf.todoapplication.viewmodel.TaskListViewModel

class TaskListViewModelFactory(private val repo: TaskRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == TaskListViewModel::class.java)
            return TaskListViewModel(repo) as T
        throw IllegalArgumentException("Unknown ViewModel class : ${modelClass.name}")
    }
}