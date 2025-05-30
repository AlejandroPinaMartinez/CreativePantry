package com.example.creativepantry

import Receta
import RecetaViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.creativepantry.analytics.UsageAnalyticsStorage
import kotlinx.coroutines.launch

class AfegirReceptaFormulari : AppCompatActivity() {

    private lateinit var recetaViewModel: RecetaViewModel
    private lateinit var usageAnalyticsStorage: UsageAnalyticsStorage


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_afegir_recepta_formulari)

        usageAnalyticsStorage = UsageAnalyticsStorage(applicationContext)

        lifecycleScope.launch {
            usageAnalyticsStorage.incrementUsageCount("Afegir")
        }

        recetaViewModel = ViewModelProvider(this)[RecetaViewModel::class.java]

        val editNombre = findViewById<EditText>(R.id.nombreReceta)
        val editUrlImagen = findViewById<EditText>(R.id.urlImagenReceta)
        val layoutIngredientes = findViewById<LinearLayout>(R.id.layoutIngredientes)
        val layoutPasos = findViewById<LinearLayout>(R.id.layoutPasos)
        val btnAddIngrediente = findViewById<Button>(R.id.btnAddIngrediente)
        val btnAddPaso = findViewById<Button>(R.id.btnAddPaso)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarReceta)

        btnAddIngrediente.setOnClickListener {
            val nuevoIngrediente = EditText(this).apply {
                hint = "Ingrediente"
                textSize = 16f
                setPadding(10, 10, 10, 10)
            }
            layoutIngredientes.addView(nuevoIngrediente)
        }

        btnAddPaso.setOnClickListener {
            val nuevoPaso = EditText(this).apply {
                hint = "Paso"
                textSize = 16f
                setPadding(10, 10, 10, 10)
            }
            layoutPasos.addView(nuevoPaso)
        }

        btnGuardar.setOnClickListener {
            val nombreReceta = editNombre.text.toString().trim()
            val urlImagen = editUrlImagen.text.toString().trim()

            if (nombreReceta.isEmpty()) {
                Toast.makeText(this, "El nombre de la receta no puede estar vacío", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (urlImagen.isEmpty()) {
                Toast.makeText(this, "La URL de la imagen no puede estar vacía", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val ingredientes = ArrayList<String>()
            for (i in 0 until layoutIngredientes.childCount) {
                val editText = layoutIngredientes.getChildAt(i) as EditText
                val ingrediente = editText.text.toString().trim()
                if (ingrediente.isNotEmpty()) {
                    ingredientes.add(ingrediente)
                }
            }

            val pasos = ArrayList<String>()
            for (i in 0 until layoutPasos.childCount) {
                val editText = layoutPasos.getChildAt(i) as EditText
                val paso = editText.text.toString().trim()
                if (paso.isNotEmpty()) {
                    pasos.add(paso)
                }
            }

            val nuevaReceta = Receta(
                titulo = nombreReceta,
                puntuacion = 0f,
                tiempo = 0,
                imagen = urlImagen,
                fav = false,
                ingredientes = ingredientes,
                pasos = pasos
            )

            try {
                recetaViewModel.addReceta(nuevaReceta)

                // Mostrar Toast de éxito y redirigir a la pantalla de Perfil
                Toast.makeText(this, "Receta añadida con éxito", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Perfil::class.java)
                startActivity(intent)
                finish()

            } catch (e: Exception) {
                // En caso de error, mostrar un Toast con el mensaje de error
                Toast.makeText(this, "Error al añadir receta: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
