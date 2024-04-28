package pe.edu.upeu.asistenciaupeujcr.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pe.edu.upeu.asistenciaupeujcr.modelo.Actividad
import pe.edu.upeu.asistenciaupeujcr.modelo.EventoAmbiental

@Dao
interface EventoAmbientalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarEventoAmbientals(eventoambiental: EventoAmbiental)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarEventoAmbientals(eventoambiental: List<EventoAmbiental>)
    @Update
    suspend fun modificarEventoAmbiental(eventoambiental: EventoAmbiental)
    @Delete
    suspend fun eliminarEventoAmbiental(eventoambiental: EventoAmbiental)
    @Query("select * from eventos_ambientales")
    fun reportatEventoAmbiental():LiveData<List<EventoAmbiental>>
    @Query("select * from eventos_ambientales where id=:idx")
    fun buscarEventoAmbiental(idx: Long):LiveData<EventoAmbiental>
}