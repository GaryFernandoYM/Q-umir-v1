package pe.edu.upeu.asistenciaupeujcr.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upeu.asistenciaupeujcr.data.local.dao.ActividadDao
import pe.edu.upeu.asistenciaupeujcr.data.local.dao.EventoAmbientalDao
import pe.edu.upeu.asistenciaupeujcr.data.local.dao.MaterialesxDao
import pe.edu.upeu.asistenciaupeujcr.data.local.dao.NoticiaAmbientalDao
import pe.edu.upeu.asistenciaupeujcr.modelo.Actividad
import pe.edu.upeu.asistenciaupeujcr.modelo.EventoAmbiental
import pe.edu.upeu.asistenciaupeujcr.modelo.Materialesx
import pe.edu.upeu.asistenciaupeujcr.modelo.NoticiaAmbiental

@Database(entities = [Actividad::class, Materialesx::class, EventoAmbiental::class, NoticiaAmbiental::class], version = 4)
abstract class DbDataSource:RoomDatabase() {
    abstract fun actividadDao():ActividadDao
    abstract fun materialesxDao(): MaterialesxDao
    abstract fun eventoambientalDao(): EventoAmbientalDao

    abstract fun noticiaAmbientalDao(): NoticiaAmbientalDao


}