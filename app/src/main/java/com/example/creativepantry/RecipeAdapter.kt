package com.example.creativepantry

import Receta
import RecetaViewModel
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

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

            // Verifica que la URL de la imagen no sea nula o vacía
            val imageUrl = receta.imagen?.trim()
            if (!imageUrl.isNullOrEmpty()) {
                // Cargar imagen con Glide, asegurando la correcta carga de imágenes externas
                Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.plato1) // Imagen mientras carga
                    .error(R.drawable.plato1) // Imagen si hay error
                    .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache para evitar recargas
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Log.e("GlideError", "Error cargando imagen: ${e?.message}")
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false // Glide manejará la imagen normalmente
                        }
                    })
                    .into(ivRecetaImagen)
            } else {
                ivRecetaImagen.setImageResource(R.drawable.plato1) // Imagen si no hay URL
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
                builder.setTitle("Opciones de receta")
                builder.setMessage("¿Qué deseas hacer con la receta ${receta.titulo}?")

                builder.setPositiveButton("Eliminar") { dialog, _ ->
                    val confirmDeleteBuilder = androidx.appcompat.app.AlertDialog.Builder(context)
                    confirmDeleteBuilder.setTitle("Confirmar eliminación")
                    confirmDeleteBuilder.setMessage("¿Estás seguro de que quieres eliminar la receta ${receta.titulo}?")

                    confirmDeleteBuilder.setPositiveButton("Eliminar") { confirmDialog, _ ->
                        receta.id_receta?.let { viewModel.deleteReceta(it) }
                        Toast.makeText(context, "Receta eliminada", Toast.LENGTH_SHORT).show()
                        confirmDialog.dismiss()
                    }

                    confirmDeleteBuilder.setNegativeButton("Cancelar") { confirmDialog, _ ->
                        confirmDialog.dismiss()
                    }

                    confirmDeleteBuilder.create().show()
                    dialog.dismiss()
                }

                builder.setNegativeButton("Modificar") { dialog, _ ->
                    val intent = Intent(context, EditarRecetaFormulari::class.java).apply {
                        putExtra("id_receta", receta.id_receta)
                        putExtra("titulo", receta.titulo)
                        putExtra("puntuacion", receta.puntuacion)
                        putExtra("tiempo", receta.tiempo)
                        putExtra("imagen", receta.imagen)
                        putStringArrayListExtra("ingredientes", ArrayList(receta.ingredientes))
                        putStringArrayListExtra("pasos", ArrayList(receta.pasos))
                    }
                    context.startActivity(intent)
                    dialog.dismiss()
                }

                builder.setNeutralButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }

                val dialog = builder.create()
                dialog.show()
            }
        }
    }
}
