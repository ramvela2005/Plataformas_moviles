package UVG.edu.gt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import UVG.edu.gt.ui.theme.UVGTheme
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UVGTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    Lab3Layout()
                }
            }
        }
    }
}

@Composable
fun Lab3Layout() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(5.dp, Color.Green)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.uvglogo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(20.dp)
                .align(Alignment.Center)
                .graphicsLayer(alpha = 0.1f)
        )
        Column(
            modifier = Modifier
                .fillMaxSize(0.95f)
                .padding(38.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(150.dp))
            Text(
                text = "Universidad del Valle de Guatemala",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                textAlign = TextAlign.Center

            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Programación de plataformas móviles, Sección 30",
                fontSize = 20.sp,
                textAlign = TextAlign.Center

            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxHeight(0.2f)) {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = "INTEGRANTES")
                Spacer(modifier = Modifier.width(35.dp))
                Column(
                    horizontalAlignment = Alignment.Start

                ) {
                    Text(text = "Carlos Samayoa")
                    Text(text = "Cristian Lopez")
                    Text(text = "Alejandra Ruiz")
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = "CATEDRÁTICO")
                Text(text = "Juan Carlos Durini")

            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Diego Ramirez"
                )
                Text(
                    textAlign = TextAlign.Center,
                    text = "23601"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Lab3LayoutPreview() {
    UVGTheme {
        Lab3Layout()
    }
}
