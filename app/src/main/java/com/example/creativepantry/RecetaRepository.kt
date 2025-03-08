package com.example.creativepantry

import Receta
import com.example.creativepantry.RetrofitClient
import retrofit2.Response

class RecetaRepository {

    suspend fun getRecetas(): Response<List<Receta>> {
        return RetrofitClient.instance.getRecetas()
    }

    suspend fun getReceta(recetaId: Int): Response<Receta> {
        return RetrofitClient.instance.getReceta(recetaId)
    }

    suspend fun addReceta(receta: Receta): Response<Receta> {
        return RetrofitClient.instance.addReceta(receta)
    }

    suspend fun deleteReceta(recetaId: Int): Response<Void> {  // Se cambió de Unit a Void
        return RetrofitClient.instance.deleteReceta(recetaId)
    }
}


