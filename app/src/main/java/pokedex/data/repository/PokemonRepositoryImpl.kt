package pokedex.data.repository

import pokedex.data.core.ResultWrapper
import pokedex.data.datasource.api.PokemonApi
import pokedex.data.datasource.mapper.toDomain
import pokedex.domain.model.PokemonDetail
import pokedex.domain.model.PokemonListResponse
import pokedex.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi
) : PokemonRepository {

    companion object {
        private const val DEFAULT_LIMIT = 20
    }

    override suspend fun getPokemonList(offset: Int): ResultWrapper<PokemonListResponse> {
        return try {
            val response = api.getPokemonList(
                limit = DEFAULT_LIMIT,
                offset = offset
            )
            ResultWrapper.Success(response.toDomain())
        } catch (e: Exception) {
            ResultWrapper.Error(message = e.message)
        }
    }

    override suspend fun getPokemonDetail(name: String): ResultWrapper<PokemonDetail> {
        return try {
            val dto = api.getPokemonDetail(name.lowercase())
            ResultWrapper.Success(dto.toDomain())
        } catch (e: Exception) {
            ResultWrapper.Error(message = e.message)
        }
    }
}
