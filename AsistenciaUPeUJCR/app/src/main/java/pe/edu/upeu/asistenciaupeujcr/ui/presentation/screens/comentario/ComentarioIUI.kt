package pe.edu.upeu.asistenciaupeujcr.ui.presentation.screens.comentario

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.google.gson.Gson
import pe.edu.upeu.asistenciaupeujcr.R
import pe.edu.upeu.asistenciaupeujcr.modelo.Comentario
import pe.edu.upeu.asistenciaupeujcr.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.BottomNavigationBar
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.ConfirmDialog
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.FabItem
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.LoadingCard
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.MultiFloatingActionButton
import pe.edu.upeu.asistenciaupeujcr.ui.presentation.components.Spacer
import pe.edu.upeu.asistenciaupeujcr.utils.TokenUtils
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ComentarioUI(
    navegarEditarComentario: (String) -> Unit,
    viewModel: ComentarioViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val comentarios by viewModel.comentarios.observeAsState(arrayListOf())
    val isLoading by viewModel.isLoading.observeAsState(false)

    MyApp(
        navController,
        onAddClick = {
            navegarEditarComentario((0).toString())
        },
        onDeleteClick = {
            viewModel.deleteComentario(it)
        },
        comentarios,
        isLoading,
        onEditClick = {
            val jsonString = Gson().toJson(it)
            navegarEditarComentario(jsonString)
        }
    )
}

val formatoFecha: DateTimeFormatter? = DateTimeFormatter.ofPattern("dd-MM-yyyy")

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp(
    navController: NavHostController,
    onAddClick: (() -> Unit)? = null,
    onDeleteClick: ((toDelete: Comentario) -> Unit)? = null,
    comentarios: List<Comentario>,
    isLoading: Boolean,
    onEditClick: ((toComentario: Comentario) -> Unit)? = null,
) {
    val context = LocalContext.current
    val navigationItems2 = listOf(
        Destinations.ComentarioUI,
        Destinations.Pantalla1,
        Destinations.Pantalla2,
        Destinations.Pantalla3
    )

    val fabItems = listOf(
        FabItem(
            Icons.Filled.ShoppingCart,
            "Shopping Cart"
        ) {
            val toast = Toast.makeText(context, "Hola Mundo", Toast.LENGTH_LONG)
            toast.view!!.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN)
            toast.show()
        },
        FabItem(
            Icons.Filled.Favorite,
            "Add Comentario"
        ) { onAddClick?.invoke() }
    )

    Scaffold(
        bottomBar = {
            BottomAppBar {
                BottomNavigationBar(navigationItems2, navController = navController)
            }
        },
        modifier = Modifier,
        floatingActionButton = {
            MultiFloatingActionButton(
                navController = navController,
                fabIcon = Icons.Filled.Add,
                items = fabItems,
                showLabels = true
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)
                    .align(alignment = Alignment.TopCenter),
                userScrollEnabled = true
            ) {
                var itemCount = comentarios.size
                if (isLoading) itemCount++
                items(count = itemCount) { index ->
                    var auxIndex = index;
                    if (isLoading) {
                        if (auxIndex == 0)
                            return@items LoadingCard()
                        auxIndex--
                    }
                    val comentario = comentarios[auxIndex]
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 1.dp
                        ),
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .fillMaxWidth(),
                    ) {
                        Row(modifier = Modifier.padding(8.dp)) {
                            Image(
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                painter = rememberImagePainter(
                                    data = comentario.contenido,
                                    builder = {
                                        placeholder(R.drawable.bg)
                                        error(R.drawable.bg)
                                    }
                                ),
                                contentDescription = null,
                                contentScale = ContentScale.FillHeight
                            )
                            Spacer()
                            Column(
                                Modifier.weight(1f),
                            ) {
                                Text("${comentario.usuario} - ${comentario.tipoEntidad}", fontWeight = FontWeight.Bold)
                                val datex = LocalDate.parse(comentario.fecha!!, DateTimeFormatter.ISO_DATE)
                                var fecha = formatoFecha?.format(datex)
                                Text("$fecha", color = MaterialTheme.colorScheme.primary)
                                Text(comentario.contenido)
                            }

                            Spacer()
                            val showDialog = remember { mutableStateOf(false) }
                            IconButton(onClick = {
                                showDialog.value = true
                            }) {
                                Icon(Icons.Filled.Delete, "Remove", tint = MaterialTheme.colorScheme.primary)
                            }
                            if (showDialog.value) {
                                ConfirmDialog(
                                    message = "¿Estás seguro de eliminar este comentario?",
                                    onConfirm = {
                                        onDeleteClick?.invoke(comentario)
                                        showDialog.value = false
                                    },
                                    onDismiss = {
                                        showDialog.value = false
                                    }
                                )
                            }

                            IconButton(onClick = {
                                Log.i("VERTOKEN", "Holas")
                                Log.i("VERTOKEN", TokenUtils.TOKEN_CONTENT)
                                onEditClick?.invoke(comentario)
                            }) {
                                Icon(
                                    Icons.Filled.Edit,
                                    contentDescription = "Editar",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun ConfirmDialog(message: String, onConfirm: () -> Unit, onDismiss: () -> Unit) {

}

