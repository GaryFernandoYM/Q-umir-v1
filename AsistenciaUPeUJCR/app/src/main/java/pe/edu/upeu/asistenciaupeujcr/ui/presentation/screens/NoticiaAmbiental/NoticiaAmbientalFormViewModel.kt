package pe.edu.upeu.asistenciaupeujcr.ui.presentation.screens.NoticiaAmbiental

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujcr.modelo.Actividad
import pe.edu.upeu.asistenciaupeujcr.modelo.ComboModel
import pe.edu.upeu.asistenciaupeujcr.modelo.EventoAmbiental
import pe.edu.upeu.asistenciaupeujcr.modelo.Materialesx
import pe.edu.upeu.asistenciaupeujcr.modelo.NoticiaAmbiental
import pe.edu.upeu.asistenciaupeujcr.repository.ActividadRepository
import pe.edu.upeu.asistenciaupeujcr.repository.EventoAmbientalRepository
import pe.edu.upeu.asistenciaupeujcr.repository.MaterialesxRepository
import pe.edu.upeu.asistenciaupeujcr.repository.NoticiaAmbientalRepository
import javax.inject.Inject

@HiltViewModel
class NoticiaAmbientalFormViewModel @Inject constructor(

    private val notRepo: NoticiaAmbientalRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getNoticiaAmbiental(idX: Long): LiveData<NoticiaAmbiental> {
        return notRepo.buscarNoticiaAmbientalId(idX)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading


    fun addEventoAmbiental(noticiaAmbiental: NoticiaAmbiental){
        viewModelScope.launch (Dispatchers.IO){
            Log.i("REAL", noticiaAmbiental.toString())
            notRepo.insertarNoticiaAmbiental(noticiaAmbiental)
        }
    }
    fun editNoticiaAmbiental(noticiaAmbiental: NoticiaAmbiental){
        viewModelScope.launch(Dispatchers.IO){
            notRepo.modificarNoticiaAmbiental(noticiaAmbiental)
        }
    }

    fun modificarNoticiaAmbiental(person: NoticiaAmbiental) {

    }
}
