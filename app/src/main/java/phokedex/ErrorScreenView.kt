package phokedex

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Vista de error estándar para mostrar mensajes de forma clara y profesional.
 *
 * @param title      Título principal del error (breve y directo).
 * @param message    Mensaje explicativo para el usuario (qué ha pasado y qué puede hacer).
 * @param showRetry  Indica si se muestra un botón secundario de reintento.
 * @param onRetry    Acción a ejecutar al pulsar "Reintentar" (si se muestra).
 * @param onBack     Acción para volver a la pantalla anterior.
 */
@Composable
fun ErrorScreenView(
    title: String,
    message: String,
    showRetry: Boolean = false,
    onRetry: (() -> Unit)? = null,
    onBack: () -> Unit
) {
    // Fondo general claro para toda la pantalla
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F6FB)),
        contentAlignment = Alignment.Center
    ) {
        // Tarjeta central donde se muestra el contenido del error
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Icono de advertencia (triángulo) representando el error
                Icon(
                    imageVector = Icons.Filled.Warning,
                    contentDescription = "Error",
                    tint = Color(0xFFFFB300), // Amarillo cálido para advertencia
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Título principal del error
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E2A3B)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Descripción más detallada y amigable para el usuario
                Text(
                    text = message,
                    fontSize = 15.sp,
                    color = Color(0xFF4A5A73),
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Botones de acción
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Botón principal para volver atrás
                    Button(
                        onClick = onBack,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Volver")
                    }

                    // Botón opcional de reintentar, solo se muestra si showRetry = true
                    if (showRetry && onRetry != null) {
                        Button(
                            onClick = onRetry,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Reintentar")
                        }
                    }
                }
            }
        }
    }
}
