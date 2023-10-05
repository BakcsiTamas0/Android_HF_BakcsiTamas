package com.example.activitylifecyclemonitor_lab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val button : Button = findViewById(R.id.button1)

        button.setOnClickListener{view ->
            val intent = Intent(view.context, ThirdActivity::class.java)
            view.context.startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Status: ", "SecondActivity.onRestartSecondActivity")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Status: ", "SecondActivity.onStartSecondActivity")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Status: ", "SecondActivity.onResumeSecondActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Status: ", "SecondActivity.onStopSecondActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Status: ", "SecondActivity.onDestroy")
    }

    override fun onPause(){
        super.onPause()
        Log.d("Status: ", "SecondActivity.onPause")
    }
}