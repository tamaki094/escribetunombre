package com.tamaki094.primeraapp.firstapp.todoapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
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
    private lateinit var fabAddTask: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)

        initComponent()
        initUI()
        initListener()
    }

    private fun initListener() {
        fabAddTask.setOnClickListener{ showDialog() }
    }
    private fun showDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)


        val btnAddtASK:Button = dialog.findViewById(R.id.btnAddTask)
        val etTask:EditText = dialog.findViewById(R.id.etTask)
        val rgCategories:RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddtASK.setOnClickListener {
            val currentTask = etTask.text.toString()
            val selected_id = rgCategories.checkedRadioButtonId
            val selectedRadioButton: RadioButton = rgCategories.findViewById<RadioButton>(selected_id)

            if(currentTask.isNotEmpty())
            {
                val currentCategory: TaskCategory = when(selectedRadioButton.text){
                    getString(R.string.dialog_category_bussiness)  -> Business
                    getString(R.string.dialog_category_personal) -> Personal
                    else -> Other
                }

                tasks.add(Task(etTask.text.toString(), currentCategory))
                dialog.hide()
            }
        }
        updateTasks()
        dialog.show()
    }

    private fun updateTasks(){
        val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected }
        val newTasks = tasks.filter { selectedCategories.contains(it.category) }
        tasksAdapter.tasks = newTasks
        tasksAdapter.notifyDataSetChanged()
    }


    private fun initComponent() {
        rvCategories = findViewById<RecyclerView>(R.id.rvCategories)
        rvTasks = findViewById<RecyclerView>(R.id.rvTasks)
        fabAddTask = findViewById<FloatingActionButton>(R.id.fabAddTask)
    }

    private fun initUI(){
        categoriesAdapter = CategoriesAdapter(categories) {position -> updateCategories(position)}
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

        tasksAdapter = TasksAdapter(tasks) {position -> onItemSelected(position)}
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = tasksAdapter
    }


    private fun onItemSelected(position: Int)
    {
        tasks[position].isSelected = !tasks[position].isSelected
        updateTasks()
    }

    private fun updateCategories(position : Int){
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTasks()
    }


}