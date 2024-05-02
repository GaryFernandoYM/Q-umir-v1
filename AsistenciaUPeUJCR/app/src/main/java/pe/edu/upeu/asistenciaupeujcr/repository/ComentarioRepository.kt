package pe.edu.upeu.asistenciaupeujcr.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujcr.data.local.dao.ComentarioDao
import pe.edu.upeu.asistenciaupeujcr.data.remote.RestComentario
import pe.edu.upeu.asistenciaupeujcr.modelo.Comentario
import pe.edu.upeu.asistenciaupeujcr.utils.TokenUtils
import pe.edu.upeu.asistenciaupeujcr.utils.isNetworkAvailable
import javax.inject.Inject

interface ComentarioRepository {
    suspend fun deleteComentario(comentario: Comentario)
    fun obtenerComentarios(): LiveData<List<Comentario>>
    fun buscarComentarioPorId(id: Long): LiveData<Comentario>
    suspend fun insertarComentario(comentario: Comentario): Boolean
    suspend fun modificarComentarioRemoto(comentario: Comentario): Boolean
    fun reportarComentarios()
}

class ComentarioRepositoryImpl @Inject constructor(
    private val restComentario: RestComentario,
    private val comentarioDao: ComentarioDao
) : ComentarioRepository {
    override suspend fun deleteComentario(comentario: Comentario) {
        CoroutineScope(Dispatchers.IO).launch {
            restComentario.deleteComentario(comentario.comentarioId)
        }
        comentarioDao.eliminarComentario(comentario)
    }

    override fun obtenerComentarios(): LiveData<List<Comentario>> {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                delay(3000)
                if (isNetworkAvailable(TokenUtils.CONTEXTO_APPX)) {
                    val data = restComentario.obtenerComentarios(TokenUtils.TOKEN_CONTENT).body()!!
                    comentarioDao.insertarComentarios(data)
                }
            }
        } catch (e: Exception) {
            Log.i("ERROR", "Error: ${e.message}")
        }
        return comentarioDao.obtenerComentarios()
    }

    override fun buscarComentarioPorId(id: Long): LiveData<Comentario> {
        return comentarioDao.buscarComentario(id)
    }

    override suspend fun insertarComentario(comentario: Comentario): Boolean {
        return restComentario.insertarComentario(TokenUtils.TOKEN_CONTENT, comentario).body() != null
    }

    override suspend fun modificarComentarioRemoto(comentario: Comentario): Boolean {
        return restComentario.actualizarComentario(TokenUtils.TOKEN_CONTENT, comentario.comentarioId, comentario).body() != null
    }

    override fun reportarComentarios() {
        TODO("Not yet implemented")
    }
}
