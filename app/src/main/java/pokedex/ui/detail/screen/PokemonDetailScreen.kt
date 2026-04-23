package pokedex.ui.detail.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pokedex.ui.state.UiState
import pokedex.ui.detail.composable.*
import pokedex.ui.detail.viewmodel.PokemonDetailViewModel


@Composable
fun PokemonDetailScreen(
    navController: NavController,
    name: String,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState(
        initial = UiState.Loading
    )
    LaunchedEffect (name) {
        viewModel.loadPokemonDetail(name)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = DetailColors.Background
    ) { padding ->

        when (val currentState = state) {

            is UiState.Loading -> DetailLoading()

            is UiState.Error -> DetailError(
                onBack = { navController.popBackStack() },
                onRetry = { viewModel.loadPokemonDetail(name) }
            )

            is UiState.Success -> {
                DetailContent(
                    pokemon = currentState.data,
                    onBack = { navController.popBackStack() },
                    modifier = Modifier.padding(padding)
                )
            }

            is UiState.Empty -> {
                DetailError(
                    onBack = { navController.popBackStack() },
                    onRetry = { viewModel.loadPokemonDetail(name) }
                )
            }
        }


    }
}
