package pokedex.ui.list.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pokedex.ui.state.UiState
import pokedex.ui.list.composable.ListColors
import pokedex.ui.list.composable.ListContent
import pokedex.ui.list.composable.ListEmpty
import pokedex.ui.list.composable.ListError
import pokedex.ui.list.composable.ListLoading
import pokedex.ui.list.viewmodel.PokemonListViewModel

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = ListColors.Background
    ) { padding ->

        when (val currentState = state) {

            is UiState.Loading -> ListLoading()

            is UiState.Error -> ListError (
                onRetry = { viewModel.retry() },
                onBack = { 
                    if (state is UiState.Error) {
                        viewModel.dismissError()
                    } else {
                        navController.popBackStack()
                    }
                }
            )

            is UiState.Empty -> ListEmpty()

            is UiState.Success -> {
                ListContent(
                    list = currentState.data,
                    onItemClick = { name ->
                        navController.navigate("detail/$name")
                    },
                    onNextPage = { viewModel.loadNextPage() },
                    onPreviousPage = { viewModel.loadPreviousPage() },
                    hasPreviousPage = viewModel.hasPreviousPage(),
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}
