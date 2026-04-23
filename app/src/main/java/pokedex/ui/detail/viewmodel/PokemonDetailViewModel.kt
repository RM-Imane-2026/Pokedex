package pokedex.ui.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pokedex.data.core.ResultWrapper
import pokedex.data.core.UiError
import pokedex.domain.repository.PokemonRepository
import pokedex.ui.state.UiState
import pokedex.ui.detail.mapper.toUi
import pokedex.ui.detail.model.PokemonDetailUi
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<UiState<PokemonDetailUi>>(UiState.Loading)
    val uiState: StateFlow<UiState<PokemonDetailUi>> = _uiState

    fun loadPokemonDetail(name: String) {
        viewModelScope.launch {

            _uiState.value = UiState.Loading

            when (val result = repository.getPokemonDetail(name)) {

                is ResultWrapper.Success -> {
                    _uiState.value =
                        UiState.Success(result.data.toUi())
                }

                is ResultWrapper.Error -> {

                    val message = when (result.error) {

                        UiError.Network ->
                            "Sin conexión"

                        UiError.NotFound ->
                            "Pokémon no encontrado"

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
