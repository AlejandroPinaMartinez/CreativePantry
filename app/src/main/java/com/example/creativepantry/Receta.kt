data class Receta(
    val id_receta: Int? = null,
    val titulo: String,
    val puntuacion: Float,
    val tiempo: Int,
    val imagen: String,
    val fav: Boolean,
    val ingredientes: List<String> = emptyList(),
    val pasos: List<String> = emptyList()
)

