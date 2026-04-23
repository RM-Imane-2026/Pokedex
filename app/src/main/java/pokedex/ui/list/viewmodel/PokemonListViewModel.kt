package pokedex.ui.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pokedex.data.core.ResultWrapper
import pokedex.data.core.UiError
import pokedex.domain.repository.PokemonRepository
import pokedex.ui.list.mapper.toUi
import pokedex.ui.list.model.PokemonItemUi
import pokedex.ui.state.UiState
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repo: PokemonRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<UiState<List<PokemonItemUi>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<PokemonItemUi>>> = _uiState

    private var currentPage = 0
    private var lastRequestedPage = 0
    private var lastSuccessData: List<PokemonItemUi>? = null

    private val pageSize = 20

    init {
        loadPage(0)
    }

    fun retry() {
        loadPage(lastRequestedPage)
    }

    fun dismissError() {
        lastSuccessData?.let {
            _uiState.value = UiState.Success(it)
        }
    }

    fun loadNextPage() {
        loadPage(currentPage + 1)
    }

    fun loadPreviousPage() {
        if (currentPage > 0) {
            loadPage(currentPage - 1)
        }
    }

    fun hasPreviousPage(): Boolean = currentPage > 0

    private fun loadPage(page: Int) {

        lastRequestedPage = page

        viewModelScope.launch {

            _uiState.value = UiState.Loading

            when (val result =
                repo.getPokemonList(offset = page * pageSize)) {

                is ResultWrapper.Success -> {

                    currentPage = page

                    val pokemonItems =
                        result.data.results.map { it.toUi() }

                    lastSuccessData = pokemonItems

                    _uiState.value =
                        if (pokemonItems.isEmpty()) {
                            UiState.Empty
                        } else {
                            UiState.Success(pokemonItems)
                        }
                }

                is ResultWrapper.Error -> {

                    val message = when (result.error) {

                        UiError.Network ->
                            "Sin conexión"

                        UiError.NotFound ->
                            "No se encontraron Pokémon"

                        is UiError.Unknown ->
                            result.error.message
                                ?: "Error desconocido"
                    }

                    _uiState.value = UiState.Error(message)
                }

                ResultWrapper.Loading -> {
                    _uiState.value = UiState.Loading
                }
            }
        }
    }
}
