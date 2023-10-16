package com.example.passwordManager

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pass3.R
import com.google.android.material.textfield.TextInputEditText

class AddRecord :AppCompatActivity(){
    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_record)

        val edTitle = findViewById<TextInputEditText>(R.id.etTitle)
        val edName = findViewById<TextInputEditText>(R.id.etUserName)
        val edPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val edWebsite = findViewById<TextInputEditText>(R.id.etWebSite)
        val edNote = findViewById<TextInputEditText>(R.id.etNote)
        val edCategory = findViewById<EditText>(R.id.etCategory)
        val btnSave = findViewById<Button>(R.id.buttonSave)

        btnSave.setOnClickListener {
            Toast.makeText(this, "hi ${edTitle.text.toString().length}", Toast.LENGTH_SHORT).show()
            if (edTitle.text.toString().isEmpty()){
                Toast.makeText(this, "k", Toast.LENGTH_SHORT).show()
                edTitle.error = "Enter a Title Name"
            }
            else{
                val db = DBHelper(this)
                val t = edTitle.text.toString()
                val n = edName.text.toString()
                val p = edPassword.text.toString()
                val w = edWebsite.text.toString()
                val note = edNote.text.toString()
                val c = edCategory.text.toString()
                val s = db.insert(t, n, p, w, note, c)
                if (s>=0){
                    finish()
                }
            }
        }
    }
}