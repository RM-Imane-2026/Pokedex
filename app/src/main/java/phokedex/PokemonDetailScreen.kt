package phokedex

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import coil.compose.AsyncImage
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PokemonDetailScreen(name: String) {

    val repository = remember { PokemonRepository() }
    var state by remember { mutableStateOf<UiState<PokemonDetail>>(UiState.Loading) }

    LaunchedEffect(Unit) {
        try {
            val data = repository.getPokemonDetail(name)
            state = UiState.Success(data)
        }catch (e: Exception) {
            e.printStackTrace()
            state = UiState.Error(e.message ?: "Error desconocido")
        }

    }

    when (state) {

        is UiState.Loading -> {
            CircularProgressIndicator()
        }

        is UiState.Error -> {
            Text("Error de conexión")
        }

        is UiState.Success -> {

            val pokemon = (state as UiState.Success<PokemonDetail>).data

            Column(modifier = Modifier.padding(16.dp)) {

                AsyncImage(
                    model = pokemon.sprites.front_default,
                    contentDescription = pokemon.name,
                    modifier = Modifier.size(150.dp)
                )

                Text("ID: ${pokemon.id}")
                Text("Nombre: ${pokemon.name}")
                Text("Altura: ${pokemon.height}")
                Text("Peso: ${pokemon.weight}")

                Text("Tipos:")
                pokemon.types.forEach {
                    Text("- ${it.type.name}")
                }
            }
        }

        else -> {}
    }
}
