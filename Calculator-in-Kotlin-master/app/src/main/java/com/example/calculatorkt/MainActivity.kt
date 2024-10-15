package com.example.calculatorkt

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.cos
import kotlin.math.tan

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private var input = ""
    private var operator = ""
    private var oldNumber = ""
    private var newNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)

        val buttons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
            R.id.buttonDot, R.id.buttonPlus, R.id.buttonMinus, R.id.buttonMultiply,
            R.id.buttonDivide, R.id.buttonEqual, R.id.buttonClear
        )

        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                onButtonClick(it as Button)
            }
        }
    }

    private fun onButtonClick(button: Button) {
        when (val buttonText = button.text.toString()) {
            "C" -> {
                input = ""
                operator = ""
                oldNumber = ""
                newNumber = ""
                tvResult.text = "0"
            }
            "=" -> {
                newNumber = input
                val result = when (operator) {
                    "+" -> oldNumber.toDouble() + newNumber.toDouble()
                    "-" -> oldNumber.toDouble() - newNumber.toDouble()
                    "*" -> oldNumber.toDouble() * newNumber.toDouble()
                    "/" -> oldNumber.toDouble() / newNumber.toDouble()
                    else -> return
                }
                tvResult.text = result.toString()
                input = result.toString()
            }
            "+", "-", "*", "/" , "%" -> {
                operator = buttonText
                oldNumber = input
                input = ""
            }
            else -> {
                input += buttonText
                tvResult.text = input
            }
        }
    }
}
