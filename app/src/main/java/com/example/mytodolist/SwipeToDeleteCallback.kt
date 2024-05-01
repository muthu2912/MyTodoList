package com.example.mytodolist

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodolist.db.TodoDao
import com.example.mytodolist.ui.MainActivityViewModel

class SwipeToDeleteCallback(
    private val viewModel: MainActivityViewModel,
    private val db: TodoDao
) :
    ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        targetViewHolder: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val todo = viewModel.todos.value?.get(position)

        if (todo != null) {
            viewModel.deleteTodo(todo, db)
        }
    }
}

