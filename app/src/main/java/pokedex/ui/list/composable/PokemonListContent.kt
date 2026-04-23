package pokedex.ui.list.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import pokedex.ui.list.model.PokemonItemUi

@Composable
fun PokemonListContent(
    list: List<PokemonItemUi>,
    onItemClick: (String) -> Unit,
    onNextPage: () -> Unit,
    onPreviousPage: () -> Unit,
    hasPreviousPage: Boolean
) {
    Column {
        LazyColumn(modifier = Modifier.weight(1f)) {

            items(list) { pokemon ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onItemClick(pokemon.name) }
                        .padding(16.dp)
                ) {

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(pokemon.image)
                            .crossfade(true)
                            .diskCachePolicy(CachePolicy.ENABLED)
                            .networkCachePolicy(CachePolicy.ENABLED)
                            .build(),
                        contentDescription = pokemon.name,
                        modifier = Modifier.size(72.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(text = "#${pokemon.id}")
                        Text(text = pokemon.name)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))

                PaginationSection(
                    onNextPage = onNextPage,
                    onPreviousPage = onPreviousPage,
                    hasPreviousPage = hasPreviousPage
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
