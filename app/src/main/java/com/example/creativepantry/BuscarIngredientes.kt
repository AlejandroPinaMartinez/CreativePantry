package com.example.creativepantry

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class BuscarIngredientes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_buscar_ingredientes)
        supportFragmentManager.beginTransaction()
            .replace(R.id.bottom_bar_container, BarraInferiorOpcions())
            .commit()
        supportFragmentManager.beginTransaction()
            .replace(R.id.top_bar_container, FragmentBarraSuperiorOpciones())
            .commit()
    }
}