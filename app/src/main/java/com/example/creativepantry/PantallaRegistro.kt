package com.example.creativepantry

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class PantallaRegistro : AppCompatActivity() {
    private lateinit var btnRegistro: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pantalla_registro)

        btnRegistro = findViewById(R.id.btnregistro)
        btnRegistro.setOnClickListener {
            // Mostrar un Toast con el mensaje
            Toast.makeText(this, "Registrado correctamente", Toast.LENGTH_SHORT).show()

            // Iniciar la nueva actividad
            val intent = Intent(this, PantallaInicioSesion::class.java)
            startActivity(intent)
        }


    }
}