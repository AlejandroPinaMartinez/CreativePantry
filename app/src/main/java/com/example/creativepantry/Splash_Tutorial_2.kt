package com.example.creativepantry

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Splash_Tutorial_2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_tutorial2)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, Splash_Tutorial_3::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}