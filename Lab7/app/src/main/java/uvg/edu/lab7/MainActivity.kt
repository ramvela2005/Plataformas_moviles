package uvg.edu.lab7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uvg.edu.lab7.ui.theme.Lab7Theme

// Data Model
data class Notification(
    val id: Int,
    val title: String,
    val description: String,
    val timestamp: String,
    val type: NotificationType
)

enum class NotificationType {
    INFORMATIVAS, CAPACITACIONES, ALERTAS, RECORDATORIOS
}

// Dummy data generation
fun generateFakeNotifications(): List<Notification> {
    return listOf(
        Notification(1, "Nueva versión disponible", "La aplicación ha sido actualizada a v1.1.0. Ve a la PlayStore y actualízala!", "19 ago • 2:30 p. m.", NotificationType.INFORMATIVAS),
        Notification(2, "Nueva capacitación", "El día Martes 21 de Agosto tendremos una nueva capacitación en el INTECAP, no faltes!", "15 ago • 3:00 p. m.", NotificationType.CAPACITACIONES),
        Notification(3, "¡Mañana capacitación de ICTA!", "No te olvides de asistir a esta capacitación mañana, a las 6pm, en el Intecap.", "05 ago • 11:30 a. m.", NotificationType.CAPACITACIONES),
        Notification(4, "Nueva versión disponible", "La aplicación ha sido actualizada a v1.0.2. Ve a la PlayStore y actualízala!", "19 jul • 2:30 p. m.", NotificationType.INFORMATIVAS)
    )
}

// Composable: NotificationTypeFilter
@Composable
fun NotificationTypeFilter(
    selectedType: NotificationType?,
    onSelectType: (NotificationType?) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(NotificationType.values()) { type ->
            FilterChip(
                selected = selectedType == type,
                onClick = {
                    if (selectedType == type) {
                        onSelectType(null) // Deselecciona si se selecciona el mismo filtro de nuevo
                    } else {
                        onSelectType(type)
                    }
                },
                label = { Text(text = type.name) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = when (type) {
                        NotificationType.INFORMATIVAS -> Color.Black
                        NotificationType.CAPACITACIONES -> Color(0xFFBBDEFB)
                        NotificationType.ALERTAS -> Color(0xFFFFCDD2)
                        NotificationType.RECORDATORIOS -> Color(0xFFFFF9C4)
                    },
                    selectedLabelColor = Color.White,
                    containerColor = Color.LightGray,
                    labelColor = Color.Black
                )
            )
        }
    }
}

// Composable: NotificationCard
@Composable
fun NotificationCard(notification: Notification) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = when (notification.type) {
                NotificationType.INFORMATIVAS -> Color(0xFFE3F2FD) // Light Blue
                NotificationType.CAPACITACIONES -> Color(0xFFE8F5E9) // Light Green
                NotificationType.ALERTAS -> Color(0xFFFFEBEE) // Light Red
                NotificationType.RECORDATORIOS -> Color(0xFFFFFDE7) // Light Yellow
            }
        ),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = when (notification.type) {
                    NotificationType.INFORMATIVAS -> Color(0xFF0D47A1) // Dark Blue
                    NotificationType.CAPACITACIONES -> Color(0xFF1B5E20) // Dark Green
                    NotificationType.ALERTAS -> Color(0xFFB71C1C) // Dark Red
                    NotificationType.RECORDATORIOS -> Color(0xFFF57F17) // Dark Yellow
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = notification.title, style = MaterialTheme.typography.titleMedium)
                Text(text = notification.description, style = MaterialTheme.typography.bodyLarge)
                Text(text = notification.timestamp, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

// Composable: NotificationScreen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(notifications: List<Notification>) {
    var selectedType by remember { mutableStateOf<NotificationType?>(null) }

    Column {
        // Barra superior con el título y la flecha
        TopAppBar(
            title = { Text(text = "Notificaciones") },
            navigationIcon = {
                IconButton(onClick = { /* Acción de la flecha, si se desea */ }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color(0xFF006E1D),
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
            )
        )

        Text(
            text = "Tipos de notificaciones",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )

        NotificationTypeFilter(selectedType) { newType ->
            selectedType = newType
        }

        LazyColumn {
            items(notifications.filter { selectedType == null || it.type == selectedType }) { notification ->
                NotificationCard(notification)
            }
        }
    }
}

// MainActivity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab7Theme {
                val notifications = generateFakeNotifications()
                NotificationScreen(notifications)
            }
        }
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab7Theme {
        val notifications = generateFakeNotifications()
        NotificationScreen(notifications)
    }
}
