package pe.edu.upeu.asistenciaupeujcr.ui.presentation.screens.comentario

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujcr.modelo.Comentario
import pe.edu.upeu.asistenciaupeujcr.repository.ComentarioRepository
import javax.inject.Inject
import kotlin.reflect.KProperty

@HiltViewModel
class ComentarioViewModel @Inject constructor(
    private val comentarioRepo: ComentarioRepository,
) : ViewModel() {
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val comentarios: LiveData<List<Comentario>> by lazy {
        comentarioRepo.reportarComentarios()
    }
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun addComentario() {
        if (_isLoading.value == false)
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.postValue(true)
            }
    }

    fun deleteComentario(toDelete: Comentario) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMINAR", toDelete.toString())
            comentarioRepo.deleteComentario(toDelete)
        }
    }
}

private operator fun Any.getValue(
    comentarioViewModel: ComentarioViewModel,
    property: KProperty<*>
): LiveData<List<Comentario>> {
    TODO("Not yet implemented")
}
