package com.example.myapplication

import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import database
import kotlin.time.DurationUnit


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = database(this)

        var list = dbHelper.getAllData()
        val listAdapter = recycleViewAdapter(list,dbHelper)

        val recycleAdapter: RecyclerView = findViewById(R.id.recyclerView)
        val addTask: Button = findViewById(R.id.addButton)
        val enteredTask: EditText = findViewById(R.id.taskInfo)

        enteredTask.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(20))

        recycleAdapter.adapter = listAdapter
        recycleAdapter.layoutManager=LinearLayoutManager(this)
        addTask.setOnClickListener{
            val taskEntered:String = enteredTask.text.toString()
             list = dbHelper.getAllData()
            if(list.contains(itemData(taskEntered))) {
                Toast.makeText(this,"Task Already Exists", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(taskEntered.equals(""))
            {
                Toast.makeText(this,"Please Enter A Task Before Adding", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            listAdapter.addItem(taskEntered,this)
            dbHelper.insertData(taskEntered);

        }
    }
}