import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.creativepantry.RecetaRepository
import kotlinx.coroutines.launch

class RecetaViewModel(private val repository: RecetaRepository) : ViewModel() {

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
                val response = repository.addReceta(receta)
                if (response.isSuccessful) {
                    cargarRecetas()
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
                    cargarRecetas()  // Refrescar lista
                } else {
                    _error.postValue("Error al eliminar receta: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _error.postValue("Error de conexión: ${e.message}")
            }
        }
    }
}
