package com.example.creativepantry.api

import Receta
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {

    @GET("/")
    suspend fun getRootMessage(): Response<Map<String, String>>

    @GET("/recetas/{receta_id}")
    suspend fun getReceta(@Path("receta_id") recetaId: Int): Response<Receta>

    @POST("/recetas")
    suspend fun addReceta(@Body receta: Receta): Response<Void>

    @DELETE("/recetas/{receta_id}")
    suspend fun deleteReceta(@Path("receta_id") recetaId: Int): Response<Map<String, Any>>  // Cambiado a Map<String, Any>

    @GET("/recetas")
    suspend fun getRecetas(): Response<List<Receta>>
}
