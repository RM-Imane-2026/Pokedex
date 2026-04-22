package pokedex.ui.list.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PaginationSection(
    onNextPage: () -> Unit,
    onPreviousPage: () -> Unit,
    hasPreviousPage: Boolean
) {

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        FilledTonalButton (
            onClick = onPreviousPage,
            enabled = hasPreviousPage,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Página anterior"
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text("Anterior")
        }

        FilledTonalButton(
            onClick = onNextPage,
            modifier = Modifier.weight(1f)
        ) {
            Text("Siguiente")
            Spacer(modifier = Modifier.width(6.dp))
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "Página siguiente"
            )
        }
    }
}
