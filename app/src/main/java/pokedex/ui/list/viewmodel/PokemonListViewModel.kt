package pokedex.ui.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pokedex.data.core.ResultWrapper
import pokedex.domain.repository.PokemonRepository
import pokedex.ui.core.state.UiState
import pokedex.ui.list.mapper.toUi
import pokedex.ui.list.model.PokemonItemUi
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repo: PokemonRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<PokemonItemUi>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<PokemonItemUi>>> = _uiState

    private var currentPage = 0
    private val pageSize = 20

    init {
        loadNextPage()
    }


    fun loadNextPage() {
        currentPage++
        loadPage()
    }

    fun loadPreviousPage() {
        if (currentPage > 0) {
            currentPage--
            loadPage()
        }
    }

    fun hasPreviousPage(): Boolean {
        return currentPage > 0
    }

    private fun loadPage() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            when (val result = repo.getPokemonList(offset = currentPage * pageSize)) {
                is ResultWrapper.Success -> {
                    val pokemonItems = result.data.results.map { it.toUi() }
                    _uiState.value = UiState.Success(pokemonItems)
                }
                is ResultWrapper.Error -> {
                    _uiState.value = UiState.Error(result.message ?: "Error de conexión")
                }
                else -> {}
            }
        }
    }

}