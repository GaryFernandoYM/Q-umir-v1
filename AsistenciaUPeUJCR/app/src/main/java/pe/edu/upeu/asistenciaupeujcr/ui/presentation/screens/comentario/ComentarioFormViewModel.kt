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

@HiltViewModel
class ComentarioFormViewModel @Inject constructor(
    private val comentarioRepo: ComentarioRepository
) : ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading

    fun addComentario(comentario: Comentario) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("REAL", comentario.toString())
            comentarioRepo.insertarComentario(comentario)
        }
    }

    //fun editComentario(comentario: Comentario) {
        //viewModelScope.launch(Dispatchers.IO) {
            //comentarioRepo.modificarComentario(comentario)
        //}
    //}
}
