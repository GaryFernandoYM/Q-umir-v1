package pe.edu.upeu.asistenciaupeujcr.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noticia_ambiental")
data class NoticiaAmbiental(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var titulo: String = "",
    var resumen: String = "",
    var contenido: String = "",
    var fechaPublicacion: String = "",
    var autor: String = "",
    var estado: String = ""
)
