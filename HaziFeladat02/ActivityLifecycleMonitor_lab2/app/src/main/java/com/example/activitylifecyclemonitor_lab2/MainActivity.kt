package com.example.activitylifecyclemonitor_lab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Status: ", "Mainactivity.onCreate")

        val button : Button = findViewById(R.id.button1)

        button.setOnClickListener { view ->
            val intent = Intent(view.context, SecondActivity::class.java)
            view.context.startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Status: ", "Mainactivity.onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Status: ", "Mainactivity.onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Status: ", "Mainactivity.onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Status: ", "Mainactivity.onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Status: ", "Mainactivity.onDestroy")
    }

    override fun onPause(){
        super.onPause()
        Log.d("Status: ", "MainActivity.onPause")
    }

}