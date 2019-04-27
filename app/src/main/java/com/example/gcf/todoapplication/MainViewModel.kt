package com.example.gcf.todoapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val tasks = MutableLiveData<List<Task>>()

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
}