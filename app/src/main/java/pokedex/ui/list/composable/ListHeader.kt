package pokedex.ui.list.composable

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding

@Composable
fun ListHeader(
    modifier: Modifier = Modifier
) {
    Text(
        text = "Listado de Pokémon",
        style = MaterialTheme.typography.titleLarge,
        color = ListColors.Primary,
        modifier = modifier.padding(vertical = 8.dp)
    )
}
