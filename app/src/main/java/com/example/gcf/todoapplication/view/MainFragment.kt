package com.example.gcf.todoapplication.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gcf.todoapplication.viewmodel.MainViewModel
import com.example.gcf.todoapplication.R
import com.example.gcf.todoapplication.viewmodel.TaskListViewModel
import com.example.gcf.todoapplication.factory.TaskListViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var viewModel: TaskListViewModel
    private lateinit var navController: NavController
    private val groupAdapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        viewModel = ViewModelProviders.of(this,
            TaskListViewModelFactory(mainViewModel.getTaskDAO())
        ).get(TaskListViewModel::class.java)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get Navigation Controller from Navigation Host in Activity
        val navHost: NavHostFragment = activity!!.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController

        // RecyclerView Configuration
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = groupAdapter

        // First load and Configuration about Observer
        viewModel.items.observe(this, Observer { items->
            groupAdapter.update(items)
        })
        viewModel.isSelectedTask.observe(this, Observer {
            if (!it) return@Observer
            viewModel.isSelectedTask.value = false
            navController.navigate(R.id.action_mainFragment_to_taskDetailFragment,
                TaskDetailFragmentArgs.Builder()
                    .setContent(viewModel.getSelectedTask())
                    .build()
                    .toBundle())
        })

        // Click Floating Action Button
        button.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_createTaskFragment)
        }
    }

    override fun onStart() {
        viewModel.load()
        super.onStart()
    }
}