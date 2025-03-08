package com.example.creativepantry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RecetaViewModel(private val repository: RecetaRepository) : ViewModel() {

    fun addReceta(receta: Receta) {
        viewModelScope.launch {
            val response = repository.addReceta(receta)
            if (response.isSuccessful) {
                println("✅ Receta añadida: ${response.body()?.titulo}")
            } else {
                println("❌ Error al añadir receta: ${response.errorBody()}")
            }
        }
    }

    fun deleteReceta(recetaId: Int) {
        viewModelScope.launch {
            val response = repository.deleteReceta(recetaId)
            if (response.isSuccessful) {
                println("✅ Receta eliminada con ID: $recetaId")
            } else {
                println("❌ Error al eliminar receta: ${response.errorBody()}")
            }
        }
    }

    fun getReceta(recetaId: Int, onResult: (Receta?) -> Unit) {
        viewModelScope.launch {
            val response = repository.getReceta(recetaId)
            if (response.isSuccessful) {
                onResult(response.body())
            } else {
                onResult(null)
                println("❌ Error al obtener receta: ${response.errorBody()}")
            }
        }
    }
}
