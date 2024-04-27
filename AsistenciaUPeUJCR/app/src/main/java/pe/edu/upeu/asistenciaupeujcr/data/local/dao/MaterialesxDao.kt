package pe.edu.upeu.asistenciaupeujcr.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import pe.edu.upeu.asistenciaupeujcr.modelo.Materialesx
import pe.edu.upeu.asistenciaupeujcr.modelo.MaterialesxConActividad

@Dao
interface MaterialesxDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarMaterialesx(materialesx: Materialesx)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarMaterialesxes(materialesx: List<Materialesx>)
    @Update
    suspend fun modificarMaterialesx(materialesx: Materialesx)
    @Query("delete from materialesx where id=:materialesx")
    suspend fun eliminarMaterialesx(materialesx: Long)
    /*@Delete
    suspend fun eliminarMaterialesx(materialesx: Materialesx)*/
    @Transaction
    @Query("select m.*, a.nombreActividad from materialesx m, actividad a where m.actividadId=a.id")
        fun reportatMaterialesx():LiveData<List<MaterialesxConActividad>>
        @Query("select * from materialesx where id=:idx")
        fun buscarMaterialesx(idx: Long): LiveData<Materialesx>
}