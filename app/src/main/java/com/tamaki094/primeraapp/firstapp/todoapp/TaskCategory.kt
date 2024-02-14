package com.tamaki094.primeraapp.firstapp.todoapp

sealed class TaskCategory
{
    object Personal: TaskCategory()
    object Business: TaskCategory()
    object Other: TaskCategory()
}