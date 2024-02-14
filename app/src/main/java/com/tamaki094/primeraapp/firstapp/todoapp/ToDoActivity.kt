package com.tamaki094.primeraapp.firstapp.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tamaki094.primeraapp.R
import com.tamaki094.primeraapp.firstapp.todoapp.TaskCategory.*

class ToDoActivity : AppCompatActivity() {
    private val categories = listOf(
        Other,
        Business,
        Personal
    )

    private val tasks = mutableListOf<Task>(
        Task("PruebaBusiness", Business),
        Task("PruebaPersonal", Personal),
            Task("PruebaOther", Other)
    )
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var rvCategories: RecyclerView
    private lateinit var rvTasks: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)

        initComponent()
        iniUI()
    }

    private fun initComponent() {
        rvCategories = findViewById<RecyclerView>(R.id.rvCategories)
        rvTasks = findViewById<RecyclerView>(R.id.rvTasks)
    }

    private fun iniUI(){
        categoriesAdapter = CategoriesAdapter(categories)
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

        tasksAdapter = TasksAdapter(tasks)
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = tasksAdapter
    }


}