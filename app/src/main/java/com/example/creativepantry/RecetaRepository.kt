package com.example.creativepantry

import com.example.creativepantry.RetrofitClient
import com.example.creativepantry.Receta
import retrofit2.Response

class RecetaRepository {

    suspend fun getReceta(recetaId: Int): Response<Receta> {
        return RetrofitClient.instance.getReceta(recetaId)
    }

    suspend fun addReceta(receta: Receta): Response<Receta> {
        return RetrofitClient.instance.addReceta(receta)
    }

    suspend fun deleteReceta(recetaId: Int): Response<Unit> {
        return RetrofitClient.instance.deleteReceta(recetaId)
    }
}

