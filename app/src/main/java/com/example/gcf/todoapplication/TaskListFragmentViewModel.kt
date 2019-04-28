package com.example.gcf.todoapplication

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_task.view.*

class TaskListFragmentViewModel : ViewModel() {
    private val tasks = MutableLiveData<List<Task>>()
    val items: LiveData<List<Item>> = Transformations.map(tasks) { tasks ->
        taskToItem(tasks)
    }
    val isSelectedTask = MutableLiveData<Boolean>().apply { postValue(false) }
    private lateinit var selectedTask: String

    fun load() {
        val data = listOf(
            Task(content = "テスト"),
            Task("日課", "毎朝起きる"),
            Task("日課", "遅刻しない"),
            Task("日課", "日付が変わる前に寝る"),
            Task("日課", "技術力を高める"),
            Task("週課", "進捗生やす"),
            Task("週課", "生存する"),
            Task("週課", "外出する")
        )
        tasks.value = data
    }

    fun getSelectedTask() = selectedTask

    private fun taskToItem(tasks: List<Task>): List<Item> {
        val items = mutableListOf<Item>()
        val clickListener: (View) -> Unit = {
            selectedTask = it.task_content.text.toString()
            Log.d("Groupie", "Click $selectedTask")
            isSelectedTask.postValue(true)
        }
        val tagList = tasks.distinctBy { task -> task.tag }.map { it.tag }
        tagList.forEach {tag ->
            items.add(HeaderItem(tag))
            tasks.filter { it.tag == tag }.forEach { items.add(TaskItem(it.content, clickListener)) }
        }
        return items
    }
}