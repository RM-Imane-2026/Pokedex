package phokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PokemonListViewModel : ViewModel() {

    private val repository = PokemonRepository()

    private val _uiState =
        MutableStateFlow<UiState<List<PokemonResult>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<PokemonResult>>> = _uiState

    private var currentPage = 0
    private val pageSize = 20

    init {
        loadNextPage()
    }

    /*
    fun loadNextPage() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading

                val response = repository.getPokemonList(
                    limit = pageSize,
                    offset = currentPage * pageSize
                )

                if (response.results.isEmpty()) {
                    _uiState.value = UiState.Empty
                } else {
                    currentPage++
                    _uiState.value = UiState.Success(response.results)
                }

            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error de conexión")
            }
        }
    }*/

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
                val response = repository.getPokemonList(
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
