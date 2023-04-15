package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list = mutableListOf(itemData("Test"))
        val listAdapter = recycleViewAdapter(list)

        val recycleAdapter: RecyclerView = findViewById(R.id.recyclerView)
        val addTask: Button = findViewById(R.id.addButton)
        val enteredTask: EditText = findViewById(R.id.taskInfo)

        enteredTask.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(20))

        recycleAdapter.adapter = listAdapter
        recycleAdapter.layoutManager=LinearLayoutManager(this)
        addTask.setOnClickListener{

            list = listAdapter.getList() as MutableList<itemData>
            listAdapter.addItem(enteredTask.text.toString())

        }
    }
}