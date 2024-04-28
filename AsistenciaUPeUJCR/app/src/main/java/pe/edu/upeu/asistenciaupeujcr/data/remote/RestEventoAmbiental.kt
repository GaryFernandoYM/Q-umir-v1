package pe.edu.upeu.asistenciaupeujcr.data.remote

import pe.edu.upeu.asistenciaupeujcr.modelo.Actividad
import pe.edu.upeu.asistenciaupeujcr.modelo.EventoAmbiental
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

interface RestEventoAmbiental {
    @GET("/asis/eventoambiental/list")
    suspend fun reportarEventoAmbiental(@Header("Authorization") token:String):Response<List<EventoAmbiental>>
    @GET("/asis/eventoambiental/buscar/{id}")
    suspend fun getEventoAmbientalId(@Header("Authorization") token:String, @Query("id") id:Long):Response<EventoAmbiental>
    @DELETE("/asis/eventoambiental/eliminar/{id}")
    suspend fun deleteEventoAmbiental(@Header("Authorization") token:String, @Path("id") id:Long):Response<MsgGeneric>
    @PUT("/asis/eventoambiental/editar/{id}")
    suspend fun actualizarEventoAmbiental(@Header("Authorization") token:String, @Path("id") id:Long, @Body eventoambiental: EventoAmbiental): Response<EventoAmbiental>
    @POST("/asis/eventoambiental/crear")
    suspend fun insertarEventoAmbiental(@Header("Authorization") token:String,@Body eventoambiental: EventoAmbiental): Response<EventoAmbiental>
}