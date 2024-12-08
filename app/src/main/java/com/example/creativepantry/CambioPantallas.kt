package com.example.creativepantry

import android.content.Intent
import android.os.Bundle
import androidx.activity.EdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.creativepantry.R
import android.widget.Button
import androidx.activity.enableEdgeToEdge

class CambioPantallas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cambio_pantallas)

        findViewById<Button>(R.id.button).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
