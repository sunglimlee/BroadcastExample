package com.example.broadcastexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var counterText : TextView
    private lateinit var minuteUpdateReceiver : BroadcastReceiver
    private var counter : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        counterText = findViewById<TextView>(R.id.counter_text)
    }

    private fun startMinuteUpdater() {
        var intentFilter : IntentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_TIME_TICK)
        //여기에서 외부에서 실행될때 BroadCast를 받는다는 set부분이 어디에 있는거지?????
        //그냥 이렇게 해놓으면 받는거가?? 여기 받는 함수도 있기는 하지만....
        //밑에 registerReceiver를 이용해서 하는구나... 오마이갓..
        minuteUpdateReceiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, p1: Intent?) {
                counter++
                counterText.setText("$counter")

            }
        }
        registerReceiver(minuteUpdateReceiver, intentFilter)
    }

    override fun onResume() {
        super.onResume()
        startMinuteUpdater()
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(minuteUpdateReceiver)
    }
}