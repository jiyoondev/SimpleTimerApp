package com.jiyoon.simpletimerapp

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.SeekBar
import android.widget.Toast
import com.jiyoon.simpletimerapp.databinding.ActivityMainBinding
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var timeTick = 0
        var minute = 0
        var second = 0
        binding.seekerBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                 timeTick = progress
                 binding.time.text = String.format("%02d : %02d", timeTick/60, timeTick%60)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                timeTick = seekBar!!.progress
                binding.time.text = String.format("%02d : %02d", timeTick/60, timeTick%60)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                timeTick = seekBar!!.progress
                binding.time.text = String.format("%02d : %02d", timeTick/60, timeTick%60)
            }
        })
        binding.start.setOnClickListener {
            minute = timeTick/60
            second = timeTick%60
            timer(period = 1000, initialDelay = 1000) {
                runOnUiThread {
                    binding.time.text = String.format("%02d : %02d", minute, second)
                }
                if (second==0 && minute==0) {
                    println("\n타이머 종료")
                    cancel()
                }
                if (second == 0) {
                    minute--
                    second = 60
                }
                second--
                binding.stop.setOnClickListener { cancel() }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val text = "Application Started"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        var timeTick = 0
        var minute = 0
        var second = 0
        binding.time.text = "00 : 00"
    }
}