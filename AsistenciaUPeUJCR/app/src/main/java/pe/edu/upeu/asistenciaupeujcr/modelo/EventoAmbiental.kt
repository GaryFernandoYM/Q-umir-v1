package pe.edu.upeu.asistenciaupeujcr.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "eventos_ambientales")
data class EventoAmbiental(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var titulo: String,
    var descripcion: String,
    var estado: String,
    var fecha: String,
    var hora: String,
    var ubicacion: String,

)
