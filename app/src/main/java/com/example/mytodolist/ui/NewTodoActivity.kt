package com.example.mytodolist.ui

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mytodolist.R
import com.example.mytodolist.db.AppDatabase
import com.example.mytodolist.models.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NewTodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_todo)

        val etTitle: EditText = findViewById(R.id.editText_title)
        val etDueDate: EditText = findViewById(R.id.editText_due_date)
        val etDesc: EditText = findViewById(R.id.editText_desc)
        val btnSubmit: Button = findViewById(R.id.btn_submit)

        val db = AppDatabase.invoke(this).todoDao()

        etDueDate.setOnClickListener {
            val c: Calendar = Calendar.getInstance()

            val year: Int = c.get(Calendar.YEAR)
            val month: Int = c.get(Calendar.MONTH)
            val day: Int = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this@NewTodoActivity,
                { _, year, monthOfYear, dayOfMonth ->
                    etDueDate.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                },
                year, month, day
            )
            datePickerDialog.show()
        }

        btnSubmit.setOnClickListener {
            GlobalScope.launch {
                db.insertTodo(
                    Todo(
                        title = etTitle.text.toString(),
                        description = etDesc.text.toString(),
                        dueDate = etDueDate.text.toString()
                    )
                )
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@NewTodoActivity, "New Todo added", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                }
            }
        }
    }
}