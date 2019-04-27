package com.example.gcf.todoapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var mainViewModel: MainViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)
        val groupAdapter = GroupAdapter< ViewHolder>()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = groupAdapter
        mainViewModel.tasks.observe(this, Observer { tasks ->
            val items = mutableListOf<Item>()
            val tagList = tasks.distinctBy { task -> task.tag }.map { it.tag }
            tagList.forEach {tag ->
                items.add(HeaderItem(tag))
                tasks.filter { it.tag == tag }.forEach { items.add(TaskItem(it.content)) }
            }
            groupAdapter.update(items)
        })
        mainViewModel.load()
    }
}