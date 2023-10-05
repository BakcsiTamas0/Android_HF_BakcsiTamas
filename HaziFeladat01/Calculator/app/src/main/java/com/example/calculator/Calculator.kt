package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentInput = StringBuilder()
    private var currentOperator: String? = null
    private var operand1: Double? = null
    private var displayText = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        display = findViewById(R.id.display)
    }

    fun onButtonClick(view: View) {
        val button = view as Button
        val buttonText = button.text.toString()

        if (buttonText == "." && currentInput.contains(".")) {
            return
        }

        if (buttonText == "DEL" && currentInput.isNotEmpty()) {
            currentInput.deleteCharAt(currentInput.length-1)
            displayText.deleteCharAt(displayText.length-1)
        } else {
            currentInput.append(buttonText)
            displayText.append(buttonText)
        }

        updateDisplay()
    }

    fun onClearClick(view: View) {
        currentInput.clear()
        currentOperator = null
        operand1 = null
        displayText.clear()
        updateDisplay()
    }

    fun onOperatorClick(view: View) {
        if (operand1 == null) {
            operand1 = currentInput.toString().toDoubleOrNull()
            currentInput.clear()
        }

        val newOperator = (view as Button).text.toString()

        if (currentOperator != null) {
            calculateResult()
        }

        currentOperator = newOperator
        displayText.append(" $newOperator ")
        updateDisplay()
    }

    fun onEqualsClick(view: View) {
        if (currentOperator != null && operand1 != null && currentInput.isNotEmpty()) {
            calculateResult()
            currentOperator = null
        }
    }

    private fun calculateResult() {
        if (currentOperator != null && operand1 != null && currentInput.isNotEmpty()) {
            val operand2 = currentInput.toString().toDoubleOrNull()

            if (operand2 != null) {
                val result = when (currentOperator) {
                    "+" -> operand1!! + operand2
                    "-" -> operand1!! - operand2
                    "*" -> operand1!! * operand2
                    "/" -> {
                        if (operand2 != 0.0) {
                            operand1!! / operand2
                        } else {
                            displayText.clear()
                            displayText.append("Error")
                            updateDisplay()
                            return
                        }
                    }
                    "%" -> (operand1!! / 100) * operand2
                    else -> 0.0
                }

                currentInput.clear()
                operand1 = result
                displayText.clear()
                displayText.append(result.toString())
                updateDisplay()
            } else {
                displayText.clear()
                displayText.append("Error")
                updateDisplay()
            }
        }
    }

    private fun updateDisplay() {
        display.text = displayText.toString()
    }
}
