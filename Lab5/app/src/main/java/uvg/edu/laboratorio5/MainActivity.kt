package uvg.edu.laboratorio5

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.benchmark.perfetto.ExperimentalPerfettoTraceProcessorApi
import androidx.benchmark.perfetto.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uvg.edu.laboratorio5.ui.theme.Laboratorio5Theme
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext



class Laboratorio5Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Laboratorio5Screen()
            }
        }
    }
}

@Composable
fun Laboratorio5Screen() {
    val context = LocalContext.current

@Composable
fun Laboratorio5Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Lunes", style = MaterialTheme.typography.headlineMedium)
        Text("25 de junio", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Card(elevation = CardDefaults.cardElevation(4.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Taco Bell", style = MaterialTheme.typography.headlineMedium)
                Text("Calzada Roosevelt, 34 avenida", style = MaterialTheme.typography.bodyMedium)
                Text("6:00 AM - 12:00 AM", style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    Button(onClick = {
                        // Mostrar toast con nombre completo
                        Toast.makeText(context, "Diego Alejandro Ramírez Velásquez", Toast.LENGTH_LONG).show()
                    }) {
                        Text("Iniciar")
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    TextButton(onClick = {
                        // Mostrar detalles del restaurante
                        Toast.makeText(context, "Comida rápida estilo mexicana\nPrecio: QQ", Toast.LENGTH_LONG).show()
                    }) {
                        Text("Detalles")
                    }

                    IconButton(onClick = {
                        // Abrir Google Maps con la ubicación del restaurante
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/Taco+Bell+%E2%80%A2+Calzada+Roosevelt/data=!4m7!3m6!1s0x8589a1b4afc99425:0xecc05f289b5167a5!8m2!3d14.6295852!4d-90.5631027!16s%2Fg%2F1hdzwrps8!19sChIJJZTJr7ShiYURpWdRmyhfwOw?authuser=0&hl=es-419&rclk=1"))
                        context.startActivity(intent)
                    }) {
                        Icon(Icons.Default.Place, contentDescription = "Directions")
                    }
                }
            }
        }
    }
}


}

@Preview(showBackground = true)
@Composable
fun PreviewLaboratorio5Screen() {
    MaterialTheme {
        Laboratorio5Screen()
    }
}