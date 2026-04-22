package pokedex.domain.repository

import pokedex.ui.core.model.PokemonListResponse
import pokedex.ui.detail.model.PokemonDetail

interface PokemonRepository {

    suspend fun getPokemonList(limit: Int, offset: Int): PokemonListResponse

    suspend fun getPokemonDetail(name: String) : PokemonDetail
}
