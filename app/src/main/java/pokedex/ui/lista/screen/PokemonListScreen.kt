package pokedex.ui.lista.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pokedex.ui.core.model.PokemonResult
import pokedex.ui.core.state.UiState
import pokedex.ui.lista.model.PokemonItem
import pokedex.ui.lista.viewmodel.PokemonListViewModel
import pokedex.ui.screen.ErrorScreenView

private var currentPage = 0
private val pageSize = 20

@Composable
fun PokemonListScreen(navController: NavController) {
    val viewModel: PokemonListViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        containerColor = Color(0xFFF4F6FB)
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = Color(0xFFF4F6FB)
        ) {
            when (state) {
                is UiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is UiState.Error -> {
                    ErrorScreenView(
                        title = "Error de conexión",
                        message = "No hemos podido cargar el listado de Pokémon.\n" +
                                "Por favor, revisa tu conexión o inténtalo de nuevo en unos instantes.",
                        showRetry = true,
                        onRetry = { viewModel.loadNextPage() },
                        onBack = { navController.popBackStack() }
                    )
                }

                is UiState.Empty -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Sin resultados",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xFF4A5A73)
                        )
                    }
                }

                is UiState.Success -> {
                    val list = (state as UiState.Success<List<PokemonResult>>).data

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "Listado de Pokémon",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xFF1E2A3B),
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        LazyColumn(
                            modifier = Modifier.weight(1f)
                        ) {
                            items(list) { pokemon ->
                                PokemonItem(
                                    id = pokemon.id,
                                    name = pokemon.formattedName,
                                    onClick = {
                                        navController.navigate("detail/${pokemon.name}")
                                    }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }

                            item {
                                Spacer(modifier = Modifier.height(12.dp))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 8.dp),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    FilledTonalButton(
                                        onClick = { viewModel.loadPreviousPage() },
                                        enabled = viewModel.hasPreviousPage(),
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
                                        onClick = { viewModel.loadNextPage() },
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

                                Spacer(modifier = Modifier.height(8.dp))

                            }
                        }
                    }
                }
            }
        }
    }
}
