package pe.edu.upeu.asistenciaupeujcr.ui.presentation.screens.comentario

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.k0shk0sh.compose.easyforms.BuildEasyForms
import com.github.k0shk0sh.compose.easyforms.EasyFormsResult
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import pe.edu.upeu.asistenciaupeujcr.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.form.AccionButtonCancel
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.form.AccionButtonSuccess
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.form.DatePickerCustom
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.form.MyFormKeys
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.form.NameTextField

@Composable
inline fun <reified Comentario> ComentarioForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: ComentarioFormViewModel = hiltViewModel()
) {
    val comentario: Comentario
    if (text != "0") {
        comentario = Gson().fromJson(text, Comentario::class.java)
    } else {
        comentario = Comentario(0, "", "", "", "")
    }
    val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    formulario(
        comentario.id!!,
        darkMode,
        navController,
        comentario,
        viewModel
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <Comentario> formulario(
    id: Long,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    comentario: Comentario,
    viewModel: ComentarioFormViewModel
) {
    Log.i("VERRR", "d: " + comentario?.id!!)
    val person = Comentario(0, "", "", "", "")
    val scope = rememberCoroutineScope()

    Scaffold(modifier = Modifier.padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)) {
        BuildEasyForms { easyForm ->
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                NameTextField(easyForms = easyForm, text = comentario?.usuario!!, "Usuario:", MyFormKeys.USER)
                NameTextField(easyForms = easyForm, text = comentario?.tipoEntidad!!, "Tipo de entidad:", MyFormKeys.TYPE_ENTITY)
                NameTextField(easyForms = easyForm, text = comentario?.contenido!!, "Contenido:", MyFormKeys.CONTENT)
                DatePickerCustom(easyForm = easyForm, label = "Fecha", texts = comentario?.fecha!!, MyFormKeys.FECHA, "yyyy-MM-dd")

                Row(Modifier.align(Alignment.CenterHorizontally)) {
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id) {
                        val lista = easyForm.formData()
                        person.usuario = (lista.get(0) as EasyFormsResult.StringResult).value
                        person.tipoEntidad = (lista.get(1) as EasyFormsResult.StringResult).value
                        person.contenido = (lista.get(2) as EasyFormsResult.StringResult).value
                        person.fecha = (lista.get(3) as EasyFormsResult.GenericStateResult<String>).value

                        if (id == 0.toLong()) {
                            Log.i("AGREGAR", "Usuario: ${person.usuario}")
                            Log.i("AGREGAR", "Tipo de entidad: ${person.tipoEntidad}")
                            Log.i("AGREGAR", "Contenido: ${person.contenido}")
                            viewModel.addComentario(person)
                        } else {
                            person.id = id
                            Log.i("MODIFICAR", "Usuario: ${person.usuario}")
                            Log.i("MODIFICAR", "Tipo de entidad: ${person.tipoEntidad}")
                            Log.i("MODIFICAR", "Contenido: ${person.contenido}")
                            viewModel.editComentario(person)
                        }
                        navController.navigate(Destinations.ComentarioUI.route)
                    }

                    AccionButtonCancel(easyForms = easyForm, "Cancelar") {
                        navController.navigate(Destinations.ComentarioUI.route)
                    }
                }
            }
        }
    }
}


