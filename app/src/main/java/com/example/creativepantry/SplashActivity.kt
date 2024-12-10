package com.example.creativepantry;

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo = findViewById<ImageView>(R.id.logoImageView)
        val slogan = findViewById<TextView>(R.id.sloganTextView)

        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        logo.startAnimation(fadeInAnimation)
        slogan.startAnimation(fadeInAnimation)

        Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }, 5000)
    }
}
