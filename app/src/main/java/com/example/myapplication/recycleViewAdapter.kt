package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import database

class recycleViewAdapter(var todos: List<itemData>, var db:database): RecyclerView.Adapter<recycleViewAdapter.viewHolder>() {


    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itesm, parent, false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun getList(): List<itemData> {

        return todos;
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.itemView.apply {
            val task:TextView = findViewById(R.id.taskTextView)
            task.text =todos[position].task
            val removeTask: Button = findViewById(R.id.Remove)
            removeTask.setOnClickListener {
                todos = todos.toMutableList().apply { removeAt(position) }.toList()
                db.removeData(task.text.toString())
                notifyDataSetChanged()
            }
        }
    }
    fun addItem(item: String, context:Context) {
        todos = todos.toMutableList().apply { add(itemData(item)) }.toList()
        notifyItemInserted(todos.size - 1) // Notify adapter that a new item has been added
    }
}