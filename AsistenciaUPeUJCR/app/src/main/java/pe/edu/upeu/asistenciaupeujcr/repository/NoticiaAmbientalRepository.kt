package pe.edu.upeu.asistenciaupeujcr.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujcr.data.local.dao.NoticiaAmbientalDao
import pe.edu.upeu.asistenciaupeujcr.data.remote.RestNoticiaAmbiental
import pe.edu.upeu.asistenciaupeujcr.modelo.NoticiaAmbiental
import pe.edu.upeu.asistenciaupeujcr.utils.TokenUtils
import pe.edu.upeu.asistenciaupeujcr.utils.isNetworkAvailable
import javax.inject.Inject

interface NoticiaAmbientalRepository {
    suspend fun deleteNoticiaAmbiental(noticia: NoticiaAmbiental)
    fun reportarNoticiaAmbientals(): LiveData<List<NoticiaAmbiental>>
    fun getNoticiaAmbientalById(id: Long): LiveData<NoticiaAmbiental>
    fun buscarNoticiaAmbientalId(id:Long): LiveData<NoticiaAmbiental>
    suspend fun insertarNoticiaAmbiental(noticia: NoticiaAmbiental): Boolean
    suspend fun modificarNoticiaAmbiental(noticia: NoticiaAmbiental): Boolean

}

 class NoticiaAmbientalRepositoryImp @Inject constructor(
    private val restNoticiaAmbiental: RestNoticiaAmbiental,
    private val noticiaAmbientalDao: NoticiaAmbientalDao
) : NoticiaAmbientalRepository {

    override suspend fun deleteNoticiaAmbiental(noticia: NoticiaAmbiental) {
        CoroutineScope(Dispatchers.IO).launch {
            restNoticiaAmbiental.deleteNoticiaAmbiental(TokenUtils.TOKEN_CONTENT, noticia.id)
        }
        noticiaAmbientalDao.eliminarNoticiaAmbiental(noticia)
    }

    override fun reportarNoticiaAmbientals(): LiveData<List<NoticiaAmbiental>> {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                delay(3000)
                if (isNetworkAvailable(TokenUtils.CONTEXTO_APPX)) {
                    val data = restNoticiaAmbiental.reportarNoticiaAmbiental(TokenUtils.TOKEN_CONTENT).body()!!
                    noticiaAmbientalDao.insertarNoticiasAmbientales(data)
                }
            }
        } catch (e: Exception) {
            Log.i("ERROR", "Error: ${e.message}")
        }
        return noticiaAmbientalDao.reportatNoticiaAmbiental()
    }

    override fun getNoticiaAmbientalById(id: Long): LiveData<NoticiaAmbiental> {
        return noticiaAmbientalDao.buscarNoticiaAmbiental(id)
    }

    override suspend fun insertarNoticiaAmbiental(noticia: NoticiaAmbiental): Boolean {
        return restNoticiaAmbiental.insertarNoticiaAmbiental(TokenUtils.TOKEN_CONTENT, noticia).body() != null
    }

    override suspend fun modificarNoticiaAmbiental(noticia: NoticiaAmbiental): Boolean {
        var dd: Boolean = false
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("VER", TokenUtils.TOKEN_CONTENT)
        }
        return restNoticiaAmbiental.actualizarNoticiaAmbiental(TokenUtils.TOKEN_CONTENT, noticia.id, noticia).body() != null
    }



     override fun buscarNoticiaAmbientalId(idX: Long): LiveData<NoticiaAmbiental> { // Implementación de la función añadida
        return noticiaAmbientalDao.buscarNoticiaAmbiental(idX)
    }
}
