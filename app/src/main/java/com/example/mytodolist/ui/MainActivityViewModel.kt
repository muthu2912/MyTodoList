package com.example.mytodolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodolist.db.TodoDao
import com.example.mytodolist.models.Todo
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {


    var todos = MutableLiveData<List<Todo>>()

    private val _showToastEvent = MutableLiveData<String>()
    val showToastEvent: LiveData<String>
        get() = _showToastEvent

    fun getAllTodos(db: TodoDao) {
        viewModelScope.launch {
            val data = db.getAll()
            todos.postValue(data)
        }
    }

    fun deleteTodo(todo: Todo, db: TodoDao) {
        viewModelScope.launch {
            db.deleteTodo(todo)
            _showToastEvent.value = "Deleted"
        }
        getAllTodos(db)
    }
}