package com.example.pass3

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class StartApp : AppCompatActivity() {
    lateinit var db: DBHelper
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_app)

        db = DBHelper(this)
        val etPass = findViewById<TextInputEditText>(R.id.etStartAppPass)
        val tvPass = findViewById<TextInputLayout>(R.id.tvStartAppPass)
        val btnEnter = findViewById<Button>(R.id.buttonEnter)

        btnEnter.setOnClickListener {
            if (db.isUserCorrect(etPass.text.toString())){
                Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
                val newPage = Intent(this, HomePage::class.java)
                startActivity(newPage)
                finish()
            }
            else{
                tvPass.helperText = "Incorrect password"
                tvPass.setHelperTextColor(ColorStateList.valueOf(Color.rgb(255,0,0)))
            }
        }
    }
}