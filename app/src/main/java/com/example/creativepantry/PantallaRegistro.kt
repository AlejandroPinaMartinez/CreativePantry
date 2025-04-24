package com.example.creativepantry

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class PantallaRegistro : AppCompatActivity() {
    private lateinit var btnRegistro: Button
    private lateinit var viewModel: RegistroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_registro)

        viewModel = ViewModelProvider(this)[RegistroViewModel::class.java]

        btnRegistro = findViewById(R.id.btnregistro)
        val nombre = findViewById<EditText>(R.id.nomUsuari)
        val correo = findViewById<EditText>(R.id.correu)
        val pass = findViewById<EditText>(R.id.contrasenya)
        val repetirPass = findViewById<EditText>(R.id.repetirContrasenya)

        viewModel.errorMessage.observe(this) { msg ->
            msg?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
        }

        viewModel.registroCorrecto.observe(this) {
            if (it) {
                Toast.makeText(this, "Registrado correctamente", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, PantallaInicioSesion::class.java))
            }
        }

        btnRegistro.setOnClickListener {
            viewModel.validarCampos(
                nombre.text.toString(),
                correo.text.toString(),
                pass.text.toString(),
                repetirPass.text.toString()
            )
        }
    }
}
