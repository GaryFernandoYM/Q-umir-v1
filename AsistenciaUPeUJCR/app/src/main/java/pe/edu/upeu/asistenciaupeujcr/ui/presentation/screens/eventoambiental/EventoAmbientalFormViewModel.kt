package pe.edu.upeu.asistenciaupeujcr.ui.presentation.screens.eventoambiental;

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujcr.modelo.EventoAmbiental
import pe.edu.upeu.asistenciaupeujcr.repository.EventoAmbientalRepository
import javax.inject.Inject

@HiltViewModel
class EventoAmbientalFormViewModel @Inject constructor(
    private val eventvRepo: EventoAmbientalRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getEventoAmbiental(idX: Long): LiveData<EventoAmbiental> {
        return eventvRepo.buscarEventoAmbientalId(idX)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading


    fun addEventoAmbiental(eventoambiental: EventoAmbiental){
        viewModelScope.launch (Dispatchers.IO){
            Log.i("REAL", eventoambiental.toString())
            eventvRepo.insertarEventoAmbiental(eventoambiental)
        }
    }
    fun editEventoAmbiental(eventoambiental: EventoAmbiental){
        viewModelScope.launch(Dispatchers.IO){
            eventvRepo.modificarRemoteEventoAmbiental(eventoambiental)
        }
    }
}
