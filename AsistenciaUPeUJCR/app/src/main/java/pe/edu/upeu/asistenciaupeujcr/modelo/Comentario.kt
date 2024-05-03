package pe.edu.upeu.asistenciaupeujcr.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comentarios")
data class Comentario(
    @PrimaryKey(autoGenerate = true)
    var comentarioId: Long = 0,
    var usuario: Long,
    var tipoEntidad: String,
    var contenido: String,
    var fecha: String
)
