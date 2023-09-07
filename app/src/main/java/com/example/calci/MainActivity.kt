package com.example.calci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.calci.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.ArithmeticException
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var lastnumeric = false
    var stateerror = false
    var lastdot = false

    private lateinit var expression: Expression
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun clear(view: View) {
        binding.datatv.text=""
        lastnumeric=false
    }
    fun back(view: View) {
        binding.datatv.text=binding.datatv.text.toString().dropLast(1)
        try {
            val lastchar = binding.datatv.text.toString().last()
            if(lastchar.isDigit()){
                onequal()
            }
        }catch (ex:Exception){
            binding.resulttv.text=""
            binding.resulttv.visibility=View.GONE
            Log.e("last char error",ex.toString())
        }
    }
    fun operator(view: View) {
        if (!stateerror&& lastnumeric){
            binding.datatv.append((view as Button).text)
            lastdot=false
            lastnumeric=false
            onequal()
        }
    }
    fun allclear(view: View) {
        binding.datatv.text=""
        binding.resulttv.text=""
        stateerror=false
        lastdot=false
        lastnumeric=false
        binding.resulttv.visibility= View.GONE
    }
    fun equal(view: View) {
        onequal()
        binding.datatv.text=binding.resulttv.text.toString().drop(1)
    }

    fun digit(view: View) {
        if (stateerror){
            binding.datatv.text=(view as Button).text
            stateerror=false

        }else{
            binding.datatv.append((view as Button).text)
        }
        lastnumeric=true
        onequal()

    }
    fun onequal(){
        if (lastnumeric && !stateerror) {
            val txt = binding.datatv.text.toString()
            expression=ExpressionBuilder(txt).build()

            try {
                val result=expression.evaluate()
                binding.resulttv.visibility=View.VISIBLE

                binding.resulttv.text= "="+result.toString()
            }
            catch (ex:ArithmeticException){
                Log.e("Evaluate error",ex.toString())
                binding.resulttv.text="Error"
                stateerror=true
                lastdot=false
            }
        }
    }
}

















