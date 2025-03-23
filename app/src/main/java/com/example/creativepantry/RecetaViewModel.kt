import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.creativepantry.repository.RecetaRepository
import kotlinx.coroutines.launch

class RecetaViewModel : ViewModel() {

    private val repository = RecetaRepository()

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
                Log.d("RecetaViewModel", "Enviando receta: $receta")

                val response = repository.addReceta(receta)

                Log.d("RecetaViewModel", "Código de respuesta: ${response.code()}")

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

    fun deleteReceta(id_receta: Int) {
        viewModelScope.launch {
            try {
                val response = repository.deleteReceta(id_receta)
                Log.d("DeleteReceta", "Código de respuesta: ${response.code()} - Cuerpo: ${response.body()}")

                if (response.isSuccessful) {
                    _recetas.value = _recetas.value?.filter { it.id_receta != id_receta }
                } else {
                    _error.postValue("Error al eliminar receta: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _error.postValue("Error de conexión: ${e.message}")
            }
        }
    }

    fun updateReceta(id_receta: Int, receta: Receta) {
        viewModelScope.launch {
            try {
                val response = repository.updateReceta(id_receta, receta)
                if (response.isSuccessful) {
                    cargarRecetas()
                } else {
                    _error.postValue("Error al actualizar receta: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _error.postValue("Error de conexión: ${e.message}")
            }
        }
    }


}
