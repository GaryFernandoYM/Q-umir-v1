package pe.edu.upeu.asistenciaupeujcr.ui.presentation.screens.NoticiaAmbiental

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujcr.modelo.EventoAmbiental
import pe.edu.upeu.asistenciaupeujcr.modelo.MaterialesxConActividad
import pe.edu.upeu.asistenciaupeujcr.modelo.NoticiaAmbiental
import pe.edu.upeu.asistenciaupeujcr.repository.EventoAmbientalRepository
import pe.edu.upeu.asistenciaupeujcr.repository.MaterialesxRepository
import pe.edu.upeu.asistenciaupeujcr.repository.NoticiaAmbientalRepository
import javax.inject.Inject

@HiltViewModel
class NoticiaAmbientalViewModel @Inject constructor(
    private val notRepo: NoticiaAmbientalRepository,
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val noticiaAmbiental: LiveData<List<NoticiaAmbiental>> by lazy {
        notRepo.reportarNoticiaAmbientals()
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addNoticiaAmbiental () {
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO) {
                _isLoading.postValue(true)
            }
    }

    fun deleteNoticiaAmbiental(toDelete: NoticiaAmbiental) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMAR", toDelete.toString())
            notRepo.deleteNoticiaAmbiental(toDelete);
        }
    }
}