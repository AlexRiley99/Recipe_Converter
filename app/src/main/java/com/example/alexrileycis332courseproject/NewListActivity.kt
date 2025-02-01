package com.example.alexrileycis332courseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

//Alex Riley
//Course Project
//07/13/2024

class NewListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_list)

        //add icon to action bar
        val actionBar = supportActionBar
        actionBar!!.setIcon(R.mipmap.ic_launcher_foreground)
        actionBar.setDisplayUseLogoEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        //get elements
        val newList = findViewById<TextView>(R.id.newList)
        val anotherButton = findViewById<Button>(R.id.convertAnother)

        //get newIngredientList from CalculationActivity
        val newIngredientList = intent.getStringArrayExtra("newIngredientList") ?: arrayOf()

        //Display new list
        newList.text = newIngredientList.joinToString("\n")

        //Return to main activity on button click
        anotherButton.setOnClickListener{
            val i = Intent(this, MainActivity2::class.java)
            startActivity(i)
        }
    }
}