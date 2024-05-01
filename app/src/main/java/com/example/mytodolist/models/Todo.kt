package com.example.mytodolist.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_list")
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String?, val description: String?,
    val dueDate: String?
)

