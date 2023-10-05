package com.example.activitylifecyclemonitor_lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Status: ", "ThirdActivity.onRestartSecondActivity")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Status: ", "ThirdActivity.onStartSecondActivity")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Status: ", "ThirdActivity.onResumeSecondActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Status: ", "ThirdActivity.onStopSecondActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Status: ", "ThirdActivity.onDestroy")
    }

    override fun onPause(){
        super.onPause()
        Log.d("Status: ", "ThirdActivity.onPause")
    }
}