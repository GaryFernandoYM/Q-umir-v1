package pe.edu.upeu.asistenciaupeujcr.ui.presentation.screens.eventoambiental

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.k0shk0sh.compose.easyforms.BuildEasyForms
import com.github.k0shk0sh.compose.easyforms.EasyFormsResult
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import pe.edu.upeu.asistenciaupeujcr.modelo.ComboModel
import pe.edu.upeu.asistenciaupeujcr.modelo.EventoAmbiental
import pe.edu.upeu.asistenciaupeujcr.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.form.AccionButtonCancel
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.form.AccionButtonSuccess
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.form.ComboBox
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.form.DatePickerCustom
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.form.MyFormKeys
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.form.NameTextField
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.form.TimePickerCustom
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.screens.actividad.splitCadena
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.screens.eventoambiental.EventoAmbientalFormViewModel



@Composable
fun EventoAmbientalForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: EventoAmbientalFormViewModel = hiltViewModel()
) {
    val eventoambientaldD:EventoAmbiental
    if (text!="0"){
        eventoambientaldD = Gson().fromJson(text, EventoAmbiental::class.java)
    }else{
        eventoambientaldD= EventoAmbiental(0,"","", "","","","")
    }
    val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    formulario(eventoambientaldD.id!!,
        darkMode,
        navController,
        eventoambientaldD,
        viewModel
    )

}



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MissingPermission",
    "CoroutineCreationDuringComposition"
)
@Composable
fun formulario(id:Long,
               darkMode: MutableState<Boolean>,
               navController: NavHostController,
               eventoAmbiental: EventoAmbiental,
               viewModel: EventoAmbientalFormViewModel
){

    Log.i("VERRR", "d: "+eventoAmbiental?.id!!)
    val person=EventoAmbiental(0,"","", "","","","")
    val context = LocalContext.current
    val scope = rememberCoroutineScope()



    Scaffold(modifier = Modifier.padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)){
        BuildEasyForms { easyForm ->
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                NameTextField(easyForms = easyForm, text =eventoAmbiental?.titulo!!,"Nomb. Evento:", MyFormKeys.NAME )
                NameTextField(easyForms = easyForm, text =eventoAmbiental?.descripcion!!,"Nomb. dcrpcionss:", MyFormKeys.ASISSUBACT )

                var listE = listOf(
                    ComboModel("Activo","Activo"),
                    ComboModel("Desactivo","Desactivo"),
                )
                ComboBox(easyForm = easyForm, "Estado:", eventoAmbiental?.estado!!, listE)
                DatePickerCustom(easyForm = easyForm, label = "Fecha", texts = eventoAmbiental?.fecha!!, MyFormKeys.FECHA,"yyyy-MM-dd")
                TimePickerCustom(easyForm = easyForm, label = "Hora", texts = eventoAmbiental?.hora!!, MyFormKeys.TIME, "HH:mm:ss")
                NameTextField(easyForms = easyForm, text = eventoAmbiental?.ubicacion!!, "Ubicacion:", MyFormKeys.UBICACION )





                Row(Modifier.align(Alignment.CenterHorizontally)){
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id){
                        val lista=easyForm.formData()
                        person.titulo=(lista.get(0) as EasyFormsResult.StringResult).value
                        person.descripcion=(lista.get(1) as EasyFormsResult.StringResult).value
                        person.estado= splitCadena((lista.get(2) as EasyFormsResult.GenericStateResult<String>).value)
                        person.fecha=(lista.get(3) as EasyFormsResult.GenericStateResult<String>).value
                        person.hora=(lista.get(4) as EasyFormsResult.GenericStateResult<String>).value
                        person.ubicacion=(lista.get(5) as EasyFormsResult.StringResult).value

                        if (id==0.toLong()){
                            Log.i("AGREGAR", "M:"+ person.titulo)
                            Log.i("AGREGAR", "VI:"+ person.descripcion)
                            Log.i("AGREGAR", "SA:"+ person.ubicacion)
                            viewModel.addEventoAmbiental(person)
                        }else{
                            person.id=id
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.editEventoAmbiental(person)
                        }
                        navController.navigate(Destinations.EventoAmbientalUI.route)
                    }

                    AccionButtonCancel(easyForms = easyForm, "Cancelar"){
                        navController.navigate(Destinations.EventoAmbientalUI.route)
                    }
                }
                // }
            }
        }
    }


    fun splitCadena(data:String):String{
        return if(data!="") data.split("-")[0] else ""
    }
}