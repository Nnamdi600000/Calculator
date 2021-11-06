package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View){
        val idInput = findViewById<TextView>(R.id.tvInput)
        idInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View){
        val idInput = findViewById<TextView>(R.id.tvInput)
        idInput.text= ""
        lastNumeric = false
        lastDot = false
    }

    fun onDPoint(view: View){
        val idInput = findViewById<TextView>(R.id.tvInput)
        if (lastNumeric &&! lastDot){
            idInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator (view: View){
        val idInput = findViewById<TextView>(R.id.tvInput)
        if (lastNumeric && !isOperator(idInput.text.toString())){
            idInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun isOperator(value: String): Boolean{
     return if (value.startsWith("-")){
         false
     }else{
         value.contains("+") || value.contains("*") || value.contains("/") || value.contains("-")
     }
    }

    private fun rZeroAfterDot(result: String): String{
      var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length - 2)
        return value
    }

    fun onEqual(view: View){
        val idInput = findViewById<TextView>(R.id.tvInput)
        if(lastNumeric){
            var tvValue = idInput.text.toString()
            var prefix = ""

            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()){
                        one = prefix + one
                    }
                    idInput.text = rZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                }else if (tvValue.contains("*")){
                    val splitValue = tvValue.split("*")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()){
                        one = prefix + one
                    }
                    idInput.text = rZeroAfterDot((one.toDouble() * two.toDouble()).toString())

                }else if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()){
                        one = prefix + one
                    }
                    idInput.text = rZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                }else if (tvValue.contains("/")){
                    val splitValue = tvValue.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()){
                        one = prefix + one
                    }
                    idInput.text = rZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }
            }catch (e: ArithmeticException){
                   e.printStackTrace()
            }
        }
    }
}