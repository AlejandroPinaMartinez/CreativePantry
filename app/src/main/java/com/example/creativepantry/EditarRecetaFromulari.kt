package com.example.creativepantry

import Receta
import RecetaViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import android.widget.*
import android.content.Intent

class EditarRecetaFormulari : AppCompatActivity() {

    private lateinit var recetaViewModel: RecetaViewModel
    private var recetaId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_receta_fromulari)

        recetaViewModel = ViewModelProvider(this)[RecetaViewModel::class.java]

        val editNombre = findViewById<EditText>(R.id.nombreReceta)
        val editUrlImagen = findViewById<EditText>(R.id.urlImagenReceta)
        val layoutIngredientes = findViewById<LinearLayout>(R.id.layoutIngredientes)
        val layoutPasos = findViewById<LinearLayout>(R.id.layoutPasos)
        val btnAddIngrediente = findViewById<Button>(R.id.btnAddIngrediente)
        val btnRemoveIngrediente = findViewById<Button>(R.id.btnRemoveIngrediente)
        val btnAddPaso = findViewById<Button>(R.id.btnAddPaso)
        val btnRemovePaso = findViewById<Button>(R.id.btnRemovePaso)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarReceta)

        // Recoger datos de la receta a editar
        recetaId = intent.getIntExtra("id_receta", 0)
        val titulo = intent.getStringExtra("titulo") ?: ""
        val imagen = intent.getStringExtra("imagen") ?: ""
        val ingredientes = intent.getStringArrayListExtra("ingredientes") ?: arrayListOf()
        val pasos = intent.getStringArrayListExtra("pasos") ?: arrayListOf()

        // Rellenar los campos con los datos de la receta
        editNombre.setText(titulo)
        editUrlImagen.setText(imagen)

        ingredientes.forEach { ingrediente ->
            val nuevoIngrediente = EditText(this).apply {
                setText(ingrediente)
                textSize = 16f
                setPadding(10, 10, 10, 10)
            }
            layoutIngredientes.addView(nuevoIngrediente)
        }

        pasos.forEach { paso ->
            val nuevoPaso = EditText(this).apply {
                setText(paso)
                textSize = 16f
                setPadding(10, 10, 10, 10)
            }
            layoutPasos.addView(nuevoPaso)
        }

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

        btnRemoveIngrediente.setOnClickListener {
            if (layoutIngredientes.childCount > 0) {
                layoutIngredientes.removeViewAt(layoutIngredientes.childCount - 1)
            }
        }

        btnRemovePaso.setOnClickListener {
            if (layoutPasos.childCount > 0) {
                layoutPasos.removeViewAt(layoutPasos.childCount - 1)
            }
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

            val ingredientesActualizados = ArrayList<String>()
            for (i in 0 until layoutIngredientes.childCount) {
                val editText = layoutIngredientes.getChildAt(i) as EditText
                val ingrediente = editText.text.toString().trim()
                if (ingrediente.isNotEmpty()) {
                    ingredientesActualizados.add(ingrediente)
                }
            }

            val pasosActualizados = ArrayList<String>()
            for (i in 0 until layoutPasos.childCount) {
                val editText = layoutPasos.getChildAt(i) as EditText
                val paso = editText.text.toString().trim()
                if (paso.isNotEmpty()) {
                    pasosActualizados.add(paso)
                }
            }

            val recetaActualizada = Receta(
                id_receta = recetaId,
                titulo = nombreReceta,
                puntuacion = 0f,
                tiempo = 0,
                imagen = urlImagen,
                fav = false,
                ingredientes = ingredientesActualizados,
                pasos = pasosActualizados
            )

            try {
                recetaViewModel.updateReceta(recetaId, recetaActualizada)
                Toast.makeText(this, "Receta actualizada con éxito", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Perfil::class.java)
                startActivity(intent)
                finish()
            } catch (e: Exception) {
                Toast.makeText(this, "Error al actualizar receta: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
