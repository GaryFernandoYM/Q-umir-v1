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
import pe.edu.upeu.asistenciaupeujcr.modelo.NoticiaAmbiental
import pe.edu.upeu.asistenciaupeujcr.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.form.AccionButtonCancel
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.form.AccionButtonSuccess
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.form.ComboBox
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.form.DatePickerCustom
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.form.MyFormKeys
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.form.NameTextField
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.form.TimePickerCustom
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.screens.NoticiaAmbiental.NoticiaAmbientalFormViewModel
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.screens.actividad.splitCadena

@Composable
fun NoticiaAmbientalForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: NoticiaAmbientalFormViewModel = hiltViewModel()
) {
    val noticiaAmbientalDao: NoticiaAmbiental
    if (text != "0") {
        noticiaAmbientalDao = Gson().fromJson(text, NoticiaAmbiental::class.java)
    } else {
        noticiaAmbientalDao = NoticiaAmbiental(0, "", "", "", "", "", "")
    }
    formulario(
        noticiaAmbientalDao.id!!,
        darkMode,
        navController,
        noticiaAmbientalDao,
        viewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MissingPermission", "CoroutineCreationDuringComposition")
@Composable
fun formulario(
    id: Long,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    noticiaAmbiental: NoticiaAmbiental,
    viewModel: NoticiaAmbientalFormViewModel
) {
    Log.i("VERRR", "d: " + noticiaAmbiental.id!!)
    val person = NoticiaAmbiental(0, "", "", "", "", "", "")
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Scaffold(modifier = Modifier.padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)) {
        BuildEasyForms { easyForm ->
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                NameTextField(easyForms = easyForm, text = noticiaAmbiental.titulo, "Nomb. Evento:", MyFormKeys.NAME)
                NameTextField(easyForms = easyForm, text = noticiaAmbiental.resumen, "Nomb. dcrpcionss:", MyFormKeys.ASISSUBACT)
                NameTextField(easyForms = easyForm, text = noticiaAmbiental.contenido, "Nomb. dcrpcionss:", MyFormKeys.CONTENIDO)

                var listE = listOf(
                    ComboModel("Activo", "Activo"),
                    ComboModel("Desactivo", "Desactivo"),
                )
                DatePickerCustom(
                    easyForm = easyForm,
                    label = "Fecha",
                    texts = noticiaAmbiental.fechaPublicacion,
                    MyFormKeys.FECHA,
                    "yyyy-MM-dd"
                )
                NameTextField(easyForms = easyForm, text = noticiaAmbiental.autor, "Nomb. dcrpcionss:", MyFormKeys.AUTOR)
                ComboBox(easyForm = easyForm, "Estado:", noticiaAmbiental.estado, listE)

                Row(Modifier.align(Alignment.CenterHorizontally)) {
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id) {
                        val lista = easyForm.formData()
                        person.titulo = (lista[0] as EasyFormsResult.StringResult).value
                        person.contenido = (lista[1] as EasyFormsResult.StringResult).value
                        person.resumen = splitCadena((lista[2] as EasyFormsResult.GenericStateResult<String>).value)
                        person.fechaPublicacion = (lista[3] as EasyFormsResult.GenericStateResult<String>).value
                        person.autor = (lista[4] as EasyFormsResult.GenericStateResult<String>).value
                        person.estado = (lista[5] as EasyFormsResult.StringResult).value

                        if (id == 0.toLong()) {
                            Log.i("AGREGAR", "M:" + person.titulo)
                            Log.i("AGREGAR", "VI:" + person.contenido)
                            Log.i("AGREGAR", "SA:" + person.resumen)
                            viewModel.addEventoAmbiental(person)
                        } else {
                            person.id = id
                            Log.i("MODIFICAR", "M:" + person)
                            viewModel.modificarNoticiaAmbiental(person)
                        }
                        navController.navigate(Destinations.EventoAmbientalUI.route)
                    }

                    AccionButtonCancel(easyForms = easyForm, "Cancelar") {
                        navController.navigate(Destinations.EventoAmbientalUI.route)
                    }
                }
            }
        }
    }

    fun splitCadena(data: String): String {
        return if (data != "") data.split("-")[0] else ""
    }
}
