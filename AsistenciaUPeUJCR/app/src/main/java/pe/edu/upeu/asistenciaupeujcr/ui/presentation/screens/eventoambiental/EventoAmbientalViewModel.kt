package pe.edu.upeu.asistenciaupeujcr.ui.presentation.screens.eventoambiental

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujcr.modelo.EventoAmbiental
import pe.edu.upeu.asistenciaupeujcr.repository.EventoAmbientalRepository
import javax.inject.Inject

@HiltViewModel
class EventoAmbientalViewModel @Inject constructor(
    private val eventvRepo: EventoAmbientalRepository,
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val eventoAmbiental: LiveData<List<EventoAmbiental>> by lazy {
        eventvRepo.reportarEventoAmbientals()
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addEventoAmbiental() {
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO) {
                _isLoading.postValue(true)
            }
    }

    fun deleteEventoAmbiental(toDelete: EventoAmbiental) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMAR", toDelete.toString())
            eventvRepo.deleteEventoAmbiental(toDelete);
        }
    }
}