package uvg.edu.lab6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uvg.edu.lab6.ui.theme.Lab6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab6Theme {
                ContadorApp()
            }
        }
    }
}

@Composable
fun ContadorApp() {
    var contador by remember { mutableStateOf(0) }
    var totalIncrementos by remember { mutableStateOf(0) }
    var totalDecrementos by remember { mutableStateOf(0) }
    var valorMaximo by remember { mutableStateOf(0) }
    var valorMinimo by remember { mutableStateOf(0) }
    var totalCambios by remember { mutableStateOf(0) }
    val historial = remember { mutableStateListOf<Pair<Int, Boolean>>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Diego Alejandro Ramírez", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(32.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {
                contador--
                totalDecrementos++
                totalCambios++
                valorMinimo = minOf(valorMinimo, contador)
                historial.add(contador to false)
            }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Decrementar", tint = Color.Blue)
            }

            Text(contador.toString(), fontSize = 48.sp, fontWeight = FontWeight.Bold)

            IconButton(onClick = {
                contador++
                totalIncrementos++
                totalCambios++
                valorMaximo = maxOf(valorMaximo, contador)
                historial.add(contador to true)
            }) {
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Incrementar", tint = Color.Blue)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Estadísticas
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Total incrementos: $totalIncrementos", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Total decrementos: $totalDecrementos", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Valor máximo: $valorMaximo", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Valor mínimo: $valorMinimo", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Total cambios: $totalCambios", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            Text("Historial:", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            LazyVerticalGrid(
                columns = GridCells.Fixed(5),
                modifier = Modifier
                    .height(250.dp)  // Limitamos la altura del historial
                    .padding(bottom = 16.dp)
            ) {
                items(historial.size) { index ->
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(
                                if (historial[index].second) Color.Green else Color.Red,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(historial[index].first.toString(), color = Color.White)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botón de Reiniciar
        Button(
            onClick = {
                contador = 0
                totalIncrementos = 0
                totalDecrementos = 0
                valorMaximo = 0
                valorMinimo = 0
                totalCambios = 0
                historial.clear()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text("Reiniciar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContadorAppPreview() {
    Lab6Theme {
        ContadorApp()
    }
}
