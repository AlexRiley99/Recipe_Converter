package com.example.alexrileycis332courseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

//Alex Riley
//Course Project
//07/13/2024

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //add icon to action bar
        val actionBar = supportActionBar
        actionBar!!.setIcon(R.mipmap.ic_launcher_foreground)
        actionBar.setDisplayUseLogoEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        //get each element
        val ingredient = findViewById<EditText>(R.id.txtIngredient)
        val quantity = findViewById<EditText>(R.id.numQuantity)
        val units = findViewById<Spinner>(R.id.unitSpinner)
        val addButton = findViewById<Button>(R.id.addButton)
        val ingredientList = findViewById<TextView>(R.id.ingredientList)
        val doneButton = findViewById<Button>(R.id.doneButton)
        val ingredientListDisplay = mutableListOf<String>()

        Log.d("MainActivity2", "onCreate: Activity started")

        //click listener for Add button
        addButton.setOnClickListener {
            val ingredientTxt = ingredient.text.toString()
            val quantityNum = quantity.text.toString()
            val unitSel = units.selectedItem.toString()

            Log.d("MainActivity2", "Add button clicked")
            Log.d("MainActivity2", "Ingredient: $ingredientTxt, Quantity: $quantityNum, Unit: $unitSel")

            if (ingredientTxt.isNotEmpty() && quantityNum.isNotEmpty()) {
                // Make user input into a string
                val ingredientString = "$ingredientTxt - $quantityNum $unitSel"
                ingredientListDisplay.add(ingredientString) // Add to list

                // Update and display list
                ingredientList.text = ingredientListDisplay.joinToString("\n")
                Log.d("MainActivity2", "List updated: ${ingredientListDisplay.joinToString("\n")}")

                // Clear inputs
                clearInputs(ingredient, quantity, units)
                Log.d("MainActivity2", "Inputs cleared")
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                Log.d("MainActivity2", "Empty fields detected")
            }
        }
        //click listener for Done button
        doneButton.setOnClickListener{
            //start calculation activity
            val i = Intent(this, CalculationActivity::class.java)
            i.putExtra("ingredientList", ingredientListDisplay.toTypedArray())
            startActivity(i)
            Log.d("MainActivity2", "Done button clicked, starting CalculationsActivity")
        }
    }
    //Clear fields
    fun clearInputs(ingredientEditText: EditText, quantityEditText: EditText, unitSpinner: Spinner) {
        ingredientEditText.text.clear()
        quantityEditText.text.clear()
        unitSpinner.setSelection(0)
        Log.d("MainActivity2", "Inputs cleared")
    }
}