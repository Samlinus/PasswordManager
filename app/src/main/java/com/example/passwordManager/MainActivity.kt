package com.example.passwordManager

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pass3.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var db: DBHelper
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate()")
        db = DBHelper(this)
        var newPage: Intent
        if (db.isFreshUser()){
            setContentView(R.layout.activity_main)
            val edPass1 = findViewById<TextInputEditText>(R.id.etPass1)
            val edPass2 = findViewById<TextInputEditText>(R.id.etPass2)
            val btnContinue = findViewById<Button>(R.id.buttonContinue)
            findViewById<TextInputLayout>(R.id.tilPass1)
            val tilPass2 = findViewById<TextInputLayout>(R.id.tilPass2)

            btnContinue.setOnClickListener {
                if (edPass1.text.toString() == edPass2.text.toString()){
                    db.insert(edPass2.text.toString())
                    // Starting Home Page
                    newPage = Intent(this, HomePage::class.java)
                    finish()
                    println("Starting activity")
                    startActivity(newPage)

                }
                else{
                    tilPass2.helperText = "Password doesn't match"
                    tilPass2.setHelperTextColor(ColorStateList.valueOf(Color.rgb(255,0,0)))
                }
            }
        }
        else{
            newPage = Intent(this, StartApp::class.java)
            finish()
            // Starting the verification page..
            startActivity(newPage)
        }
    }



    override fun onPause() {
        super.onPause()
        println("onPause()")
    }


    override fun onStop() {
        super.onStop()
        println("onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart()")
    }

    override fun onStart() {
        super.onStart()
        println("onStart()")
    }

    override fun onResume() {
        super.onResume()
        println("onResume()")
    }

}