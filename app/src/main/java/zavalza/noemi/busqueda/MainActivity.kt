package zavalza.noemi.busqueda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zavalza.noemi.busqueda.ui.theme.BusquedaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusquedaTheme {
                // Usamos Scaffold para estructurar la UI
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        BuscarPantalla(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun BuscarPantalla(modifier: Modifier = Modifier) {
    // Estado para manejar la pregunta del usuario y la respuesta
    val pregunta = remember { mutableStateOf(TextFieldValue()) }
    val respuesta = remember { mutableStateOf("La respuesta aparecerá aquí.") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Campo de texto para la pregunta
        BasicTextField(
            value = pregunta.value,
            onValueChange = { pregunta.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Gray),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Botón de búsqueda
        Button(onClick = {
            // Lógica para buscar la respuesta
            respuesta.value = buscarRespuesta(pregunta.value.text)
        }) {
            Text(text = "Buscar")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Mostrar la respuesta
        Text(text = respuesta.value, modifier = Modifier.padding(16.dp))
    }
}

// Función simple de búsqueda de respuesta
fun buscarRespuesta(pregunta: String): String {
    return when {
        pregunta.contains("hola", ignoreCase = true) -> "¡Hola! ¿Cómo puedo ayudarte?"
        pregunta.contains("cómo estás", ignoreCase = true) -> "Estoy bien, gracias por preguntar."
        pregunta.isEmpty() -> "Por favor, escribe una pregunta."
        else -> "Lo siento, no tengo una respuesta para esa pregunta."
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusquedaTheme {
        BuscarPantalla()
    }
}
