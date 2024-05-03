package pe.edu.upeu.asistenciaupeujcr.data.remote

import pe.edu.upeu.asistenciaupeujcr.modelo.EventoAmbiental
import pe.edu.upeu.asistenciaupeujcr.modelo.NoticiaAmbiental
import pe.edu.upeu.asistenciaupeujcr.modelo.MsgGeneric
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RestNoticiaAmbiental {
    @GET("/asis/noticia_ambiental/list")
    suspend fun getNoticiasAmbientales (@Header("Authorization") token:String): Response<List<NoticiaAmbiental>>
    suspend fun reportarNoticiaAmbiental(@Header("Authorization") token:String):Response<List<NoticiaAmbiental>>

    @GET("/asis/noticiaambiental/buscar/{id}")
    suspend fun getNoticiaAmbientalById(@Header("Authorization") token:String, @Path("id") id:Long): Response<NoticiaAmbiental>

    @DELETE("/asis/noticiaambiental/eliminar/{id}")
    suspend fun deleteNoticiaAmbiental(@Header("Authorization") token:String, @Path("id") id:Long): Response<MsgGeneric>

    @PUT("/asis/noticiaambiental/editar/{id}")
    suspend fun actualizarNoticiaAmbiental(@Header("Authorization") token:String, @Path("id") id:Long, @Body noticia: NoticiaAmbiental): Response<NoticiaAmbiental>

    @POST("/asis/noticiaambiental/crear")
    suspend fun insertarNoticiaAmbiental(@Header("Authorization") token:String,@Body noticia: NoticiaAmbiental): Response<NoticiaAmbiental>
}
