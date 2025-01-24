package com.example.creativepantry

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(
    private val recipeList: List<Receta>,
    private val context: Context // Agregamos el contexto aquí
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_receta, parent, false)
        return RecipeViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipeList.size

    class RecipeViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {
        private val ivRecetaImagen: ImageView = itemView.findViewById(R.id.ivRecipeImage)
        private val tvRecetaTitulo: TextView = itemView.findViewById(R.id.tvRecetaTitulo)
        private val tvRecetaDetalles: TextView = itemView.findViewById(R.id.tvRecetaDetalles)

        fun bind(receta: Receta) {
            tvRecetaTitulo.text = receta.titulo
            tvRecetaDetalles.text = "${receta.puntuacion}★  ${receta.tiempo}m"
            val image_name = receta.imagen

            val resource_id = context.resources.getIdentifier(image_name, "drawable", context.packageName)
            if (resource_id != 0) {
                ivRecetaImagen.setImageResource(resource_id)
            } else {
                ivRecetaImagen.setImageResource(R.drawable.plato1)
            }
        }
    }
}
