package pokedex.ui.lista.model

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PokemonItem(
    id: Int,
    name: String,
    onClick: () -> Unit
) {

    val imageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {

        AsyncImage(
            model = imageUrl,
            contentDescription = name,
            modifier = Modifier.size(72.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(text = "#$id")
            Text(text = name)
        }
    }
}
