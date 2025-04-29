package com.example.creativepantry

import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class PantallaRegistro : AppCompatActivity() {

    private val viewModel: RegistroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_registro)

        val nomUsuari = findViewById<EditText>(R.id.nomUsuari)
        val correu = findViewById<EditText>(R.id.correu)
        val contrasenya = findViewById<EditText>(R.id.contrasenya)
        val repetirContrasenya = findViewById<EditText>(R.id.repetirContrasenya)
        val btnRegistro = findViewById<Button>(R.id.btnregistro)
        val txtError = findViewById<TextView>(R.id.txtError)

        // Observa los cambios de errorMessage
        viewModel.errorMessage.observe(this, Observer { error ->
            if (error != null) {
                txtError.text = error
                txtError.visibility = TextView.VISIBLE
            } else {
                txtError.text = ""
                txtError.visibility = TextView.GONE
            }
        })

        // Puedes observar registroCorrecto si necesitas hacer algo más
        viewModel.registroCorrecto.observe(this, Observer { correcto ->
            if (correcto == true) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            }
        })

        btnRegistro.setOnClickListener {
            viewModel.validarCampos(
                nomUsuari.text.toString(),
                correu.text.toString(),
                contrasenya.text.toString(),
                repetirContrasenya.text.toString()
            )
        }
    }
}
