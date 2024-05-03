package pe.edu.upeu.asistenciaupeujcr.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pe.edu.upeu.asistenciaupeujcr.data.remote.RestComentario
import pe.edu.upeu.asistenciaupeujcr.modelo.Comentario

@Dao
interface ComentarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarComentario(comentario: Comentario)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarComentarios(comentarios: List<RestComentario.Comentario>)

    @Update
    suspend fun modificarComentario(comentario: Comentario)

    @Delete
    suspend fun eliminarComentario(comentario: Comentario)

    @Query("SELECT * FROM comentarios")
    fun obtenerComentarios(): LiveData<List<Comentario>>

    @Query("SELECT * FROM comentarios WHERE comentarioId = :id")
    fun buscarComentario(id: Long): LiveData<Comentario>
}
