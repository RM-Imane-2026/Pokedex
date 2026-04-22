package phokedex

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.*

@Composable
fun PokemonListScreen(navController: NavController) {

    val viewModel: PokemonListViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()

    when (state) {

        is UiState.Loading -> {
            CircularProgressIndicator()
        }

        is UiState.Error -> {
            Column{
                Text("Error de conexión")
                Button(onClick = { viewModel.loadNextPage() }) {
                    Text("Reintentar")
                }
            }
        }

        is UiState.Empty -> {
            Text("Sin resultados")
        }

        is UiState.Success -> {

            val list = (state as UiState.Success<List<PokemonResult>>).data

            LazyColumn {

                items(list) { pokemon ->

                    PokemonItem(
                        id = pokemon.id,
                        name = pokemon.formattedName,
                        onClick = {
                            navController.navigate("detail/${pokemon.name}")
                        }
                    )
                }

                item {
                    Button(onClick = { viewModel.loadNextPage() }) {
                        Text("Cargar más")
                    }
                }
            }

        }
    }
}
