package com.example.pr10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.*
import java.math.BigInteger


//Реализуйте программу вычисления f(n!) со всеми десятичными знака-
//ми, где n ∈ [1 . . . 13], где fn – числа Фибоначчи, f1 = f2 = 1.


class Calculations{
    private var job: Job? = null
    private fun calc(input: Int): BigInteger {  // вычисления
        return if(input==1) 1.toBigInteger()
        else {
            val factorial: BigInteger = // Факториал
                generateSequence(Pair(1, 2)) { Pair(it.first * it.second, it.second + 1) }
                    .takeWhile { (it.second - 1) <= input }.map { it.first }.last().toBigInteger()
            generateSequence(Triple(1.toBigInteger(), 1.toBigInteger(), 2.toBigInteger()))
            { Triple(it.second, it.first + it.second, it.third + 1.toBigInteger()) }
                .takeWhile { (it.third <= factorial) && !(job?.isCancelled!!) }.map { it.second }
                .last()
        }
    }

    fun startCalculations(editTextInputNumber: Int) { // Начло вычисления
        job = GlobalScope.launch {
            val resnum = calc(editTextInputNumber)
            listenerAnswer?.receiveAnswer(resnum)
        }
    }

    fun stopCalculations(){ // Конец вычислений
        job?.cancel()
    }

    private var listenerAnswer: ListenerAnswer? = null

    fun register(listenerAnswer: ListenerAnswer){ // слушает
        this.listenerAnswer = listenerAnswer
    }

    companion object {
        private var calculations: Calculations? = null
        fun getInstance():Calculations{
            if (calculations == null)
                calculations = Calculations()
            return calculations!!
        }
    }

}

interface ListenerAnswer{
     fun receiveAnswer(answer: BigInteger) //гарантия, что есть функция которая получает ответ
}

class MainActivity : AppCompatActivity(),ListenerAnswer {
    private lateinit var textView : TextView
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (textView.text != "")
            outState.putString(RESULT,textView.text.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Calculations.getInstance().register(this)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.buttonCalculate)
        textView = findViewById<TextView>(R.id.textViewRes)
        val editText = findViewById<EditText>(R.id.editTextNumber)
        val buttonStop = findViewById<Button>(R.id.buttonStop)

        if (savedInstanceState != null){
            textView.text = savedInstanceState.getString("RESULT").toString()
        }

        button.setOnClickListener { // По кнопке
            if (editText.text.toString().toIntOrNull() in 1..13)
                Calculations.getInstance().startCalculations(editText.
                text.toString().toInt())
            else Toast.makeText(this, getString(R.string.Incorrectnum), Toast.LENGTH_SHORT).show()
        }
        buttonStop.setOnClickListener {
            Calculations.getInstance().stopCalculations() // Остановка вычислений
        }

    }
    override fun receiveAnswer(answer: BigInteger) { // вывод ответа
        GlobalScope.launch(Dispatchers.Main) {
            textView.text = answer.toString()
        }
    }
    companion object{
        const val RESULT = "RESULT"
    }

}