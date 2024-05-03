package pe.edu.upeu.asistenciaupeujcr.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pe.edu.upeu.asistenciaupeujcr.modelo.EventoAmbiental
import pe.edu.upeu.asistenciaupeujcr.modelo.NoticiaAmbiental

@Dao
interface NoticiaAmbientalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarNoticiaAmbiental(noticia: NoticiaAmbiental)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarNoticiasAmbientales(noticia:List<NoticiaAmbiental>)

    @Update
    suspend fun modificarNoticiaAmbiental(noticia: NoticiaAmbiental)

    @Delete
    suspend fun eliminarNoticiaAmbiental(noticia: NoticiaAmbiental)

    @Query("SELECT * FROM noticia_ambiental")
    fun getNoticiasAmbientales(): LiveData<List<NoticiaAmbiental>>

    @Query("SELECT * FROM noticia_ambiental WHERE id = :id")
    fun buscarNoticiaAmbiental(id: Long): LiveData<NoticiaAmbiental>
    @Query("select * from noticia_ambiental")
    fun reportatNoticiaAmbiental():LiveData<List<NoticiaAmbiental>>
}
