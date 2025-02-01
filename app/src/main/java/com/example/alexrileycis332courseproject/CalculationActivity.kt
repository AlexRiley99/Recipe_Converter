package com.example.alexrileycis332courseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast

//Alex Riley
//Course Project
//07/13/2024

class CalculationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculation)

        //add icon to action bar
        val actionBar = supportActionBar
        actionBar!!.setIcon(R.mipmap.ic_launcher_foreground)
        actionBar.setDisplayUseLogoEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        //get elements
        val multiplyRadio = findViewById<RadioButton>(R.id.multiplyRadio)
        val divideRadio = findViewById<RadioButton>(R.id.divideRadio)
        val howMany = findViewById<EditText>(R.id.howManyNum)
        val calcButton = findViewById<Button>(R.id.calculateButton)

        //Get list from MainActivity2
        val ingredientList = intent.getStringArrayExtra("ingredientList") ?: arrayOf()

        //Array to store new ingredient list
        val newIngredientList = mutableListOf<String>()

        //click listener for Calculate button
        calcButton.setOnClickListener {
            try {
                val howManyNum = howMany.text.toString().toInt()

                //cycle through ingredientList
                for (ingredient in ingredientList) {
                    //Split ingredient into parts
                    val parts = ingredient.split(" - ")

                    if(parts.size < 2){
                        Toast.makeText(this, "Invalid format for: $ingredient", Toast.LENGTH_SHORT).show()
                        continue
                    }

                    //get ingredient name to pass to newIngredientList
                    val ingredientName = parts[0].trim()

                    //get the quantity and unit
                    val quantityPart = parts[1].trim()
                    val quantityAndUnit = quantityPart.split(" ")
                    val quantityString = quantityAndUnit[0]
                    val unitString = quantityAndUnit.drop(1).joinToString(" ")

                    val currentQuantity = quantityString.toDoubleOrNull()
                    if(currentQuantity != null) {
                        //calculate new quantity
                        val newQuantity = if (multiplyRadio.isChecked) {
                            currentQuantity * howManyNum
                        } else {
                            currentQuantity / howManyNum
                        }

                        //create updated ingredient string
                        val updatedIngredient = "$ingredientName - $newQuantity $unitString"
                        newIngredientList.add(updatedIngredient) //add to newIngredientList
                    }
                    else{
                        Toast.makeText(this, "Invalid quantity format for: $ingredient", Toast.LENGTH_SHORT).show()
                        Log.e("CalculationError", "Error for ingredient: $ingredient")
                    }
                }

                //Pass results to NewListActivity
                val i = Intent(this, NewListActivity::class.java)
                i.putExtra("newIngredientList", newIngredientList.toTypedArray())
                startActivity(i)
            }catch (e: Exception){
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.e("Calculation Exception", "Exception: ${e.message}", e)
            }
        }
    }
}