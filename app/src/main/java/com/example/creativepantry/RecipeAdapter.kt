package com.example.creativepantry

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(
    private val recipeList: List<Receta>,
    private val context: Context,
    private val viewModel: RecetaViewModel
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_receta, parent, false)
        return RecipeViewHolder(view, context, viewModel)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipeList.size

    class RecipeViewHolder(
        itemView: View,
        private val context: Context,
        private val viewModel: RecetaViewModel
    ) : RecyclerView.ViewHolder(itemView) {

        private val ivRecetaImagen: ImageView = itemView.findViewById(R.id.ivRecipeImage)
        private val tvRecetaTitulo: TextView = itemView.findViewById(R.id.tvRecetaTitulo)
        private val tvRecetaDetalles: TextView = itemView.findViewById(R.id.tvRecetaDetalles)
        private val btnVerReceta: Button = itemView.findViewById(R.id.btnReceta)
        private val btnAddReceta: Button = itemView.findViewById(R.id.btnAddReceta)
        private val btnDeleteReceta: Button = itemView.findViewById(R.id.btnDeleteReceta)

        fun bind(receta: Receta) {
            tvRecetaTitulo.text = receta.titulo
            tvRecetaDetalles.text = "${receta.puntuacion}★  ${receta.tiempo}m"

            val resourceId = context.resources.getIdentifier(receta.imagen, "drawable", context.packageName)
            ivRecetaImagen.setImageResource(if (resourceId != 0) resourceId else R.drawable.plato1)

            btnVerReceta.setOnClickListener {
                val intent = Intent(context, Detall::class.java)
                intent.putExtra("titulo", receta.titulo)
                intent.putExtra("puntuacion", receta.puntuacion)
                intent.putExtra("tiempo", receta.tiempo)
                intent.putExtra("imagen", receta.imagen)
                context.startActivity(intent)
            }

            btnAddReceta.setOnClickListener {
                viewModel.addReceta(receta)
                Toast.makeText(context, "Añadiendo receta: ${receta.titulo}", Toast.LENGTH_SHORT).show()
            }

            btnDeleteReceta.setOnClickListener {
                viewModel.deleteReceta(receta.id)
                Toast.makeText(context, "Eliminando receta: ${receta.titulo}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
