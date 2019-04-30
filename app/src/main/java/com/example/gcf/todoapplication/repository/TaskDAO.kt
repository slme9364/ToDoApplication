package com.example.gcf.todoapplication.repository

import androidx.room.*
import com.example.gcf.todoapplication.Task

@Dao
interface TaskDAO : TaskRepository {
    @Query("select * from task")
    override fun getAll(): List<Task>

    @Query("select * from task where content = :content")
    override fun getTaskByContent(content: String): Task?

    @Insert
    override fun insert(task: Task)

    @Delete
    override fun delete(task: Task)
}