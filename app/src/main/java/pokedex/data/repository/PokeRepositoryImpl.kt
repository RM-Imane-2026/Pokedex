package pokedex.data.repository

import pokedex.data.datasource.api.PokeApi
import pokedex.domain.repository.PokemonRepository
import pokedex.ui.core.model.PokemonListResponse
import pokedex.ui.detail.model.PokemonDetail
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApi
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): PokemonListResponse {
        return api.getPokemonList(limit, offset)
    }

    override suspend fun getPokemonDetail(name: String): PokemonDetail {
        return api.getPokemonDetail(name)
    }
}
