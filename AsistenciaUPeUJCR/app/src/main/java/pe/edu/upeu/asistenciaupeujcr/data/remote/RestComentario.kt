package pe.edu.upeu.asistenciaupeujcr.data.remote


import pe.edu.upeu.asistenciaupeujcr.modelo.MsgGeneric
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RestComentario {
    @GET("/asis/comentario/list")
    suspend fun obtenerComentarios(@Header("Authorization") token: String): Response<List<Comentario>>

    class Comentario {

    }

    @GET("/asis/comentario/buscar/{id}")
    suspend fun obtenerComentarioPorId(@Header("Authorization") token: String, @Path("id") id: Long): Response<Comentario>

    @DELETE("/asis/comentario/eliminar/{id}")
    suspend fun eliminarComentario(@Header("Authorization") token: String, @Path("id") id: Long): Response<MsgGeneric>

    @PUT("/asis/comentario/editar/{id}")
    suspend fun actualizarComentario(@Header("Authorization") token: String, @Path("id") id: Long, @Body comentario: pe.edu.upeu.asistenciaupeujcr.modelo.Comentario): Response<Comentario>

    @POST("/asis/comentario/crear")
    suspend fun insertarComentario(@Header("Authorization") token: String, @Body comentario: pe.edu.upeu.asistenciaupeujcr.modelo.Comentario): Response<Comentario>
    abstract fun deleteComentario(comentarioId: Long)
}
