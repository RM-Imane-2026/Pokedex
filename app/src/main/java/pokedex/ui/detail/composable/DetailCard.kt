package pokedex.ui.detail.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pokedex.ui.detail.model.PokemonDetailUi

@Composable
fun DetailCard(pokemon: PokemonDetailUi) {

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = pokemon.image,
                contentDescription = pokemon.name,
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("ID: ${pokemon.id}", fontWeight = FontWeight.SemiBold)
            Text("Nombre: ${pokemon.name}", fontWeight = FontWeight.SemiBold)

            Spacer(modifier = Modifier.height(8.dp))

            Text("Altura: ${pokemon.height}")
            Text("Peso: ${pokemon.weight}")

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            DetailTypesSection(types = pokemon.types)
        }
    }
}
