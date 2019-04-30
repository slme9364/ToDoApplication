package com.example.gcf.todoapplication.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.gcf.todoapplication.item.HeaderItem
import com.example.gcf.todoapplication.Task
import com.example.gcf.todoapplication.item.TaskItem
import com.example.gcf.todoapplication.repository.TaskRepository
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_task.view.*

class TaskListViewModel(val repo: TaskRepository) : ViewModel() {
    private val tasks = MutableLiveData<List<Task>>()
    val items: LiveData<List<Item>> = Transformations.map(tasks) { tasks ->
        taskToItem(tasks)
    }
    val isSelectedTask = MutableLiveData<Boolean>().apply { postValue(false) }
    private lateinit var selectedTask: String

    fun load() {
        tasks.value = repo.getAll()
    }

    fun getSelectedTask() = selectedTask

    private fun taskToItem(tasks: List<Task>): List<Item> {
        val items = mutableListOf<Item>()
        val clickListener: (View) -> Unit = {
            selectedTask = it.task_content.text.toString()
            Log.d("Groupie", "Click $selectedTask")
            isSelectedTask.value = true
        }
        val tagList = tasks.distinctBy { task -> task.tag }.map { it.tag }
        tagList.forEach {tag ->
            items.add(HeaderItem(tag))
            tasks.filter { it.tag == tag }.forEach { items.add(
                TaskItem(
                    it.content,
                    clickListener
                )
            ) }
        }
        return items
    }
}