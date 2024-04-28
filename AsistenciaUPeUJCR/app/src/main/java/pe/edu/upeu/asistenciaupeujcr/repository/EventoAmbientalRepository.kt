package pe.edu.upeu.asistenciaupeujcr.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujcr.data.local.dao.EventoAmbientalDao
import pe.edu.upeu.asistenciaupeujcr.data.remote.RestEventoAmbiental
import pe.edu.upeu.asistenciaupeujcr.modelo.EventoAmbiental
import pe.edu.upeu.asistenciaupeujcr.utils.TokenUtils
import pe.edu.upeu.asistenciaupeujcr.utils.isNetworkAvailable
import javax.inject.Inject

interface EventoAmbientalRepository {
    suspend fun deleteEventoAmbiental(eventoambiental: EventoAmbiental)
    fun reportarEventoAmbientals():LiveData<List<EventoAmbiental>>
    fun buscarEventoAmbientalId(id:Long):LiveData<EventoAmbiental>
    suspend fun insertarEventoAmbiental(eventoambiental: EventoAmbiental):Boolean
    suspend fun modificarRemoteEventoAmbiental(eventoambiental: EventoAmbiental):Boolean
}



class EventoAmbientalRepositoryImp @Inject constructor(
    private val restEventoAmbiental: RestEventoAmbiental,
    private val eventoAmbientalDao: EventoAmbientalDao
): EventoAmbientalRepository{
    override suspend fun deleteEventoAmbiental(eventoambiental: EventoAmbiental){
        CoroutineScope(Dispatchers.IO).launch {
            restEventoAmbiental.deleteEventoAmbiental(TokenUtils.TOKEN_CONTENT,eventoambiental.id)
        }
        eventoAmbientalDao.eliminarEventoAmbiental(eventoambiental)
    }
    override fun reportarEventoAmbientals():LiveData<List<EventoAmbiental>>{
        try {
            CoroutineScope(Dispatchers.IO).launch{
                delay(3000)
                if (isNetworkAvailable(TokenUtils.CONTEXTO_APPX)){
                    val data=restEventoAmbiental.reportarEventoAmbiental(TokenUtils.TOKEN_CONTENT).body()!!
                    eventoAmbientalDao.insertarEventoAmbientals(data)
                }
            }
        }catch (e:Exception){
            Log.i("ERROR", "Error: ${e.message}")
        }
        return eventoAmbientalDao.reportatEventoAmbiental()
    }
    override fun buscarEventoAmbientalId(id:Long):LiveData<EventoAmbiental>{
        return eventoAmbientalDao.buscarEventoAmbiental(id)
    }
    override suspend fun insertarEventoAmbiental(eventoambiental: EventoAmbiental):Boolean{
        return restEventoAmbiental.insertarEventoAmbiental(TokenUtils.TOKEN_CONTENT,
            eventoambiental).body()!=null
    }
    override suspend fun modificarRemoteEventoAmbiental(eventoambiental: EventoAmbiental):Boolean{
        var dd:Boolean=false
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("VER", TokenUtils.TOKEN_CONTENT)
        }
        return restEventoAmbiental.actualizarEventoAmbiental(TokenUtils.TOKEN_CONTENT, eventoambiental.id, eventoambiental).body()!=null
    }
}