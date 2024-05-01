package com.example.mytodolist.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mytodolist.models.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_list ORDER BY id DESC")
    suspend fun getAll(): List<Todo>

    @Insert
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)
}