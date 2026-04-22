package pokedex.domain.repository

import pokedex.data.core.ResultWrapper
import pokedex.domain.model.PokemonDetail
import pokedex.domain.model.PokemonListResponse

interface PokemonRepository {

    suspend fun getPokemonList(offset: Int): ResultWrapper<PokemonListResponse>

    suspend fun getPokemonDetail(name: String): ResultWrapper<PokemonDetail>
}
