package pokedex.ui.detail.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
@Composable
fun DetailHeader(
    name: String,
    onBack: () -> Unit
) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton (onClick = onBack) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                tint = DetailColors.Primary
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "Detalles de $name",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = DetailColors.Primary
        )
    }
}
