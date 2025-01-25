package com.example.creativepantry

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class PantallaInicioSesion : AppCompatActivity() {
    private lateinit var btnIniciarSesion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pantalla_inicio_sesion)

        btnIniciarSesion = findViewById(R.id.btniniciarSesion)
        btnIniciarSesion.setOnClickListener {
            val intent = Intent(this, BuscarIngredientes::class.java)
            startActivity(intent)
        }
    }
}