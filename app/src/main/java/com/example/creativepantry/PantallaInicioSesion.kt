package com.example.creativepantry

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class PantallaInicioSesion : AppCompatActivity() {
    private lateinit var btnIniciarSesion: Button
    private lateinit var editUsername: EditText
    private lateinit var editPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pantalla_inicio_sesion)

        editUsername = findViewById(R.id.username)
        editPassword = findViewById(R.id.password)
        btnIniciarSesion = findViewById(R.id.btniniciarSesion)

        btnIniciarSesion.setOnClickListener {
            val username = editUsername.text.toString().trim()
            val password = editPassword.text.toString().trim()

            if (username.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa un nombre de usuario", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            UsuarioPreferences.saveUsername(this, username)

            val intent = Intent(this, BuscarIngredientes::class.java)
            startActivity(intent)
            finish()
        }
    }
}
