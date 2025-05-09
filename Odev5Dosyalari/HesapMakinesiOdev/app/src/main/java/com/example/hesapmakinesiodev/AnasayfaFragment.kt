package com.example.calculatorkotlinwithandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hesapmakinesiodev.databinding.FragmentAnasayfaBinding

class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding
    private var expression = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false)

        val numberButtons = listOf(
            binding.button0, binding.button1, binding.button2,
            binding.button3, binding.button4, binding.button5,
            binding.button6, binding.button7, binding.button8,
            binding.button9
        )

        numberButtons.forEach { button ->
            button.setOnClickListener {
                expression += button.text
                updateDisplay()
            }
        }

        binding.buttonArti.setOnClickListener { addOperator("+") }
        binding.buttonEksi.setOnClickListener { addOperator("-") }
        binding.buttonx.setOnClickListener { addOperator("*") }
        binding.buttonBolu.setOnClickListener { addOperator("/") }

        binding.buttonAC.setOnClickListener {
            expression = ""
            updateDisplay()
        }

        binding.buttonbackspace.setOnClickListener {
            if (expression.isNotEmpty()) {
                expression = expression.dropLast(1)
                updateDisplay()
            }
        }

        binding.buttonEsit.setOnClickListener {
            calculateResult()
        }

        return binding.root
    }

    private fun updateDisplay() {
        binding.textView2.text = expression
    }

    private fun addOperator(op: String) {
        if (expression.isNotEmpty() && !"+-*/".contains(expression.last())) {
            expression += op
            updateDisplay()
        }
    }

    private fun calculateResult() {
        try {
            val result = eval(expression)
            binding.textView2.text = if (result % 1.0 == 0.0) {
                result.toInt().toString()
            } else {
                result.toString()
            }
            expression = result.toString()
        } catch (e: Exception) {
            binding.textView2.text = "Hata"
            expression = ""
        }
    }

    // Basit işlem hesaplayıcı (sadece + - * / sırasına göre)
    private fun eval(expr: String): Double {
        val tokens = mutableListOf<String>()
        var number = ""

        for (ch in expr) {
            if (ch in "0123456789.") {
                number += ch
            } else if (ch in "+-*/") {
                if (number.isNotEmpty()) {
                    tokens.add(number)
                    number = ""
                }
                tokens.add(ch.toString())
            }
        }
        if (number.isNotEmpty()) tokens.add(number)

        // Çarpma / Bölme Önceliği
        var i = 0
        while (i < tokens.size) {
            if (tokens[i] == "*" || tokens[i] == "/") {
                val left = tokens[i - 1].toDouble()
                val right = tokens[i + 1].toDouble()
                val result = if (tokens[i] == "*") left * right else left / right
                tokens[i - 1] = result.toString()
                tokens.removeAt(i) // operator
                tokens.removeAt(i) // right
                i--
            } else {
                i++
            }
        }

        // Toplama / Çıkarma
        var result = tokens[0].toDouble()
        i = 1
        while (i < tokens.size) {
            val op = tokens[i]
            val num = tokens[i + 1].toDouble()
            result = when (op) {
                "+" -> result + num
                "-" -> result - num
                else -> result
            }
            i += 2
        }

        return result
    }
}