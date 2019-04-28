package com.example.gcf.todoapplication

import android.view.View
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_task.*

class TaskItem(private val content: String, private val clickListener: (View) -> Unit) : Item() {
    override fun getLayout() = R.layout.item_task

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.task_content.text = content
        viewHolder.itemView.setOnClickListener(clickListener)
    }
}