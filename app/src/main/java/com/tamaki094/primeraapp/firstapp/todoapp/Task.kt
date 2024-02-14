package com.tamaki094.primeraapp.firstapp.todoapp

data class Task(val name:String, val category:TaskCategory, var isSelected:Boolean = false) {

}