import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.creativepantry.repository.RecetaRepository
import kotlinx.coroutines.launch

class RecetaViewModel : ViewModel() {

    private val repository = RecetaRepository()  // Inicializar el repositorio directamente

    private val _recetas = MutableLiveData<List<Receta>>()
    val recetas: LiveData<List<Receta>> get() = _recetas

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun cargarRecetas() {
        viewModelScope.launch {
            try {
                val response = repository.getRecetas()
                if (response.isSuccessful) {
                    _recetas.postValue(response.body() ?: emptyList())
                } else {
                    _error.postValue("Error al obtener recetas: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _error.postValue("Error de conexión: ${e.message}")
            }
        }
    }

    fun addReceta(receta: Receta) {
        viewModelScope.launch {
            try {
                // Registra lo que estás enviando para depurar
                Log.d("RecetaViewModel", "Enviando receta: $receta")

                // Envía la receta al repositorio
                val response = repository.addReceta(receta)

                // Registra el código de respuesta de la API para depurar
                Log.d("RecetaViewModel", "Código de respuesta: ${response.code()}")

                if (response.isSuccessful) {
                    cargarRecetas()  // Recarga las recetas si la creación fue exitosa
                } else {
                    _error.postValue("Error al añadir receta: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _error.postValue("Error de conexión: ${e.message}")
            }
        }
    }



    fun deleteReceta(recetaId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.deleteReceta(recetaId)
                if (response.isSuccessful) {
                    cargarRecetas()
                } else {
                    _error.postValue("Error al eliminar receta: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _error.postValue("Error de conexión: ${e.message}")
            }
        }
    }
}
