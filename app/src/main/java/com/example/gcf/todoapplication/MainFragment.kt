package com.example.gcf.todoapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var viewModel: TaskListFragmentViewModel
    private val groupAdapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(TaskListFragmentViewModel::class.java)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get Navigation Controller from Navigation Host in Activity
        val navHost: NavHostFragment = activity!!.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHost.navController

        // RecyclerView Configuration
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = groupAdapter

        // First load and Configuration about Observer
        viewModel.items.observe(this, Observer { items->
            groupAdapter.update(items)
        })
        viewModel.isSelectedTask.observe(this, Observer {
            if (!it) return@Observer
            Log.d("INFO","$it")
            Log.d("INFO", viewModel.getSelectedTask())
            viewModel.isSelectedTask.postValue(false)
            navController.navigate(R.id.action_mainFragment_to_createTaskFragment)
        })
    }

    override fun onStart() {
        viewModel.load()
        super.onStart()
    }
}