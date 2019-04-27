package com.example.gcf.todoapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.xwray.groupie.kotlinandroidextensions.Item

class MainViewModel : ViewModel() {
    private val tasks = MutableLiveData<List<Task>>()
    val items: LiveData<List<Item>> = Transformations.map(tasks) { tasks ->
        taskToItem(tasks)
    }

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

    private fun taskToItem(tasks: List<Task>): List<Item> {
        val items = mutableListOf<Item>()
        val tagList = tasks.distinctBy { task -> task.tag }.map { it.tag }
        tagList.forEach {tag ->
            items.add(HeaderItem(tag))
            tasks.filter { it.tag == tag }.forEach { items.add(TaskItem(it.content)) }
        }
        return items
    }
}