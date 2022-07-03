package com.example.mytodolist

interface ToDoListListener {
    fun onItemClick(position: Int)
    fun onItemLongClick(position: Int)
    fun onDeleteItemClick(position: Int) {
    }
}