package com.example.pass3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class HomePage : AppCompatActivity() {
    private var db = DBHelper(this)
    private lateinit var recyclerView: RecyclerView
    private var itemlist =  ArrayList<Item>()
    private lateinit var itemAdapter: RecyclerAdapter
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page1)


        val btnAdd = findViewById<Button>(R.id.buttonAdd)

        btnAdd.setOnClickListener {
            val newPage = Intent(this, AddRecord::class.java)
            startActivity(newPage)
        }

        recyclerView = findViewById(R.id.recycler_view)
        searchView = findViewById(R.id.search_View)
        itemAdapter = RecyclerAdapter(itemlist)
        setItems()


        setQueryListener()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)


        recyclerView.adapter = itemAdapter
        itemAdapter.setOnItemClickListener(object: RecyclerAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@HomePage,"You clicked on Item no. $position",Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun setQueryListener(){
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
    }
    private fun filterList(newText: String?) {
        val filtered_list = ArrayList<Item>()
        if (newText != null) {
            for (items in itemlist) {
                if (items.Title.lowercase(Locale.getDefault())
                        .contains(newText.toString(), ignoreCase = true))

                    filtered_list.add(items)
            }
        }
        if(filtered_list.isEmpty()){
            Toast.makeText(this,"No data found",Toast.LENGTH_LONG).show()
        }
        else{
            itemAdapter.setFilteredList(filtered_list)
        }

    }
    private fun setItems(){
        this.itemlist = db.retrieveData()
        itemAdapter.setFilteredList(this.itemlist)
    }

    override fun onResume() {
        super.onResume()
        println("page1: onResume()")
        setItems()
        println("item set..")
    }

    override fun onBackPressed() {
        println("Back pressed..")
        finish()
    }

}