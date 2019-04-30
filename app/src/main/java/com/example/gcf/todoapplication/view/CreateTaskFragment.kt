package com.example.gcf.todoapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.gcf.todoapplication.*
import com.example.gcf.todoapplication.factory.CreateTaskViewModelFactory
import com.example.gcf.todoapplication.viewmodel.CreateTaskViewModel
import com.example.gcf.todoapplication.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_create_task.*

class CreateTaskFragment : Fragment(R.layout.fragment_create_task) {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var viewModel: CreateTaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        viewModel = ViewModelProviders.of(this,
            CreateTaskViewModelFactory(mainViewModel.getTaskDAO())
        ).get(CreateTaskViewModel::class.java)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        save_button.setOnClickListener {
            val content = editContent.text
            if (content.isEmpty()) {
                Toast.makeText(this.context, "TODOを入力してください", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val tag = if (editTag.text.isEmpty()) "未分類" else editTag.text

            val task = Task(tag.toString(), content.toString())
            viewModel.createTask(task)
            fragmentManager?.popBackStack()
        }
    }
}