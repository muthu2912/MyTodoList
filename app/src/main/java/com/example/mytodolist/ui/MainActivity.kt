package com.example.mytodolist.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodolist.R
import com.example.mytodolist.SwipeToDeleteCallback
import com.example.mytodolist.adapters.TodoListAdapter
import com.example.mytodolist.db.AppDatabase
import com.example.mytodolist.db.TodoDao


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var todoRecycler: RecyclerView
    private lateinit var db: TodoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        db = AppDatabase.invoke(this).todoDao()

        initViews()

    }

    override fun onStart() {
        super.onStart()
        val db = AppDatabase.invoke(this).todoDao()
        viewModel.getAllTodos(db)
        viewModel.todos.observe(this) { data ->
            todoRecycler.adapter = TodoListAdapter(data)
        }
    }

    private fun initViews() {
        todoRecycler = findViewById(R.id.todo_recycler)
        todoRecycler.layoutManager = LinearLayoutManager(this)
        val swipeCallback = SwipeToDeleteCallback(viewModel, db)
        val itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(todoRecycler)

        viewModel.showToastEvent.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        val fab: View = findViewById(R.id.new_todo)
        fab.setOnClickListener {
            val intent = Intent(this, NewTodoActivity::class.java)
            startActivity(intent)
        }
    }


}

