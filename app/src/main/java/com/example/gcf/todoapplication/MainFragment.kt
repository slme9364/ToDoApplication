package com.example.gcf.todoapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var mainViewModel: MainViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)

        // RecyclerView Configuration
        val groupAdapter = GroupAdapter< ViewHolder>()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = groupAdapter

        // First load and Configuration about Observer
        mainViewModel.items.observe(this, Observer { items->
            groupAdapter.update(items)
        })
        mainViewModel.load()
    }
}