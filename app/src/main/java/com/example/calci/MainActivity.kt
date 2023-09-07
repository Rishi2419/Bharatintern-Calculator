package com.example.calci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clear(view: View) {}
    fun back(view: View) {}
    fun operator(view: View) {}
    fun allclear(view: View) {}
    fun equal(view: View) {}
    fun digit(view: View) {}
}