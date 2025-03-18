package com.example.creativepantry

import Receta
import RecetaViewModel
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
    private var recipeList: List<Receta>,
    private val context: Context,
    private val viewModel: RecetaViewModel
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    fun updateRecetas(nuevaLista: List<Receta>) {
        recipeList = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_receta, parent, false)
        return RecipeViewHolder(view, context, viewModel)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipeList[position])
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
        private val btnDeleteReceta: Button = itemView.findViewById(R.id.btnDeleteReceta)

        fun bind(receta: Receta) {
            tvRecetaTitulo.text = receta.titulo
            tvRecetaDetalles.text = "${receta.puntuacion}★  ${receta.tiempo}m"

            val resourceId = context.resources.getIdentifier(receta.imagen, "drawable", context.packageName)
            if (resourceId != 0) {
                ivRecetaImagen.setImageResource(resourceId)
            } else {
                ivRecetaImagen.setImageResource(R.drawable.plato1)
            }

            btnVerReceta.setOnClickListener {
                val intent = Intent(context, Detall::class.java).apply {
                    putExtra("titulo", receta.titulo)
                    putExtra("puntuacion", receta.puntuacion)
                    putExtra("tiempo", receta.tiempo)
                    putExtra("imagen", receta.imagen)
                    putStringArrayListExtra("ingredientes", ArrayList(receta.ingredientes))
                    putStringArrayListExtra("pasos", ArrayList(receta.pasos))
                }
                context.startActivity(intent)
            }

            btnDeleteReceta.setOnClickListener {
                val builder = androidx.appcompat.app.AlertDialog.Builder(context)
                builder.setTitle("Confirmar eliminación")
                builder.setMessage("¿Estás seguro de que quieres eliminar la receta ${receta.titulo}?")

                builder.setPositiveButton("Eliminar") { dialog, _ ->
                    viewModel.deleteReceta(receta.id)
                    Toast.makeText(context, "Receta eliminada", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }

                builder.setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }

                val dialog = builder.create()
                dialog.show()
            }
        }
    }
}
