package com.example.mytodolist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodolist.R
import com.example.mytodolist.models.Todo

class TodoListAdapter(val todoList: List<Todo>) :
    RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = this.itemView.findViewById(R.id.textView_title)
        val tvDueDate: TextView = this.itemView.findViewById(R.id.textView_due_date)
        val tvDesc: TextView = this.itemView.findViewById(R.id.textView_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_todo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoList.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = todoList[position]

        holder.tvTitle.text = item.title
        holder.tvDueDate.text = item.dueDate
        holder.tvDesc.text = item.description
    }
}