package com.example.gcf.todoapplication

import android.util.Log
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_tag.*

class HeaderItem(private val header: String) : Item() {
    override fun getLayout() = R.layout.item_tag

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.header_tag.text = header
    }
}