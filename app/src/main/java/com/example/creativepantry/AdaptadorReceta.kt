package com.example.creativepantry

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(private val recipeList: List<Receta>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_receta, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipeList.size

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivRecetaImagen: ImageView = itemView.findViewById(R.id.ivRecipeImage)
        private val tvRecetaTitulo: TextView = itemView.findViewById(R.id.tvRecetaTitulo)
        private val tvRecetaDetalles: TextView = itemView.findViewById(R.id.tvRecetaDetalles)

        fun bind(receta: Receta) {
            tvRecetaTitulo.text = receta.titulo
            tvRecetaDetalles.text = "${receta.puntuacion}★  ${receta.tiempo}m"

        }
    }
}