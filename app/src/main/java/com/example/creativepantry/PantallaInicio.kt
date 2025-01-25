package com.example.creativepantry

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PantallaInicio : AppCompatActivity() {

    private lateinit var btnIniciarSesion: Button
    private lateinit var btnRegistro: Button
    private lateinit var btnInvitado: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_inicio)

        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        btnRegistro = findViewById(R.id.btnRegistro)
        btnInvitado = findViewById(R.id.btnInvitado)

        btnIniciarSesion.setOnClickListener {
            val intent = Intent(this, PantallaInicioSesion::class.java)
            startActivity(intent)
        }

        btnRegistro.setOnClickListener {
            val intent = Intent(this, PantallaRegistro::class.java)
            startActivity(intent)
        }

        btnInvitado.setOnClickListener {
            val intent = Intent(this, BuscarIngredientes::class.java)
            startActivity(intent)
        }
    }
}
