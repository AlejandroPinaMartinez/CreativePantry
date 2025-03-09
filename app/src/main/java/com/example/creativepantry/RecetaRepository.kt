package com.example.creativepantry.repository

import com.example.creativepantry.RetrofitClient
import Receta
import retrofit2.Response

class RecetaRepository {

    suspend fun getRecetas(): Response<List<Receta>> =
        RetrofitClient.instance.getRecetas()

    suspend fun getReceta(recetaId: Int): Response<Receta> =
        RetrofitClient.instance.getReceta(recetaId)

    suspend fun addReceta(receta: Receta): Response<Void> =
        RetrofitClient.instance.addReceta(receta)

    suspend fun deleteReceta(recetaId: Int): Response<Map<String, Any>> =
        RetrofitClient.instance.deleteReceta(recetaId)
}
