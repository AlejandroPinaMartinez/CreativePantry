package com.example.creativepantry

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class Filtrado : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_filtrado)

        //recyclerView = findViewById(R.id.recyclerView)
        //recyclerView.layoutManager = LinearLayoutManager(this)

        val listaRecetas = cargarRecetas()
        recipeAdapter = RecipeAdapter(listaRecetas)
        recyclerView.adapter = recipeAdapter

        supportFragmentManager.beginTransaction()
            .replace(R.id.bottom_bar_container, BarraInferiorOpcions())
            .commit()
        supportFragmentManager.beginTransaction()
            .replace(R.id.top_bar_container, FragmentBarraSuperiorOpciones())
            .commit()
    }

    private fun cargarRecetas(): List<Receta> {
        return listOf(
            Receta("Arroz a la cubana", 4.8f, 20, "@drawable/plato1"),
            Receta("Bacalao", 3.3f, 30, "@drawable/plato2")
        )
    }
}