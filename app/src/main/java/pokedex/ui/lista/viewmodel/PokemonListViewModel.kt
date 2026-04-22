package pokedex.ui.lista.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pokedex.domain.repository.PokemonRepository
import pokedex.ui.core.state.UiState
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repo: PokemonRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<UiState<List<Any>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Any>>> = _uiState

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

            try {
                val response = repo.getPokemonList(
                    limit = pageSize,
                    offset = currentPage * pageSize
                )

                _uiState.value = UiState.Success(response.results)

            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error de conexión")
            }
        }
    }

}