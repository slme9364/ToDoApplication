package com.example.gcf.todoapplication.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.gcf.todoapplication.R
import com.example.gcf.todoapplication.databinding.FragmentDetailTaskBinding
import com.example.gcf.todoapplication.factory.TaskDetailViewModelFactory
import com.example.gcf.todoapplication.viewmodel.MainViewModel
import com.example.gcf.todoapplication.viewmodel.TaskDetailViewModel
import kotlinx.android.synthetic.main.fragment_detail_task.*

class TaskDetailFragment : Fragment(R.layout.fragment_detail_task) {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var viewModel: TaskDetailViewModel
    private lateinit var binding: FragmentDetailTaskBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        viewModel = ViewModelProviders.of(this, TaskDetailViewModelFactory(mainViewModel.getTaskDAO())).get(TaskDetailViewModel::class.java)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = TaskDetailFragmentArgs.fromBundle(arguments!!)
        val task = viewModel.getTask(args.content)
        binding = bindView()
        binding.task = task
        super.onViewCreated(view, savedInstanceState)

        delete_button.setOnClickListener {
            AlertDialog.Builder(this.context!!)
                .setTitle("確認")
                .setMessage("削除しますか")
                .setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
                    viewModel.delete(task!!)
                    fragmentManager?.popBackStack()
                })
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    fun <T: ViewDataBinding> Fragment.bindView(): T = DataBindingUtil.bind(view!!)!!
}