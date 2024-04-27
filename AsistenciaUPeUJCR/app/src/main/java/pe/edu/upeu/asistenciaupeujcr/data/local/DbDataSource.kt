package pe.edu.upeu.asistenciaupeujcr.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upeu.asistenciaupeujcr.data.local.dao.ActividadDao
import pe.edu.upeu.asistenciaupeujcr.data.local.dao.MaterialesxDao
import pe.edu.upeu.asistenciaupeujcr.modelo.Actividad
import pe.edu.upeu.asistenciaupeujcr.modelo.Materialesx

@Database(entities = [Actividad::class, Materialesx::class], version = 2)
abstract class DbDataSource:RoomDatabase() {
    abstract fun actividadDao():ActividadDao
    abstract fun materialesxDao(): MaterialesxDao
}