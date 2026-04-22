package phokedex

class PokemonRepository {

    suspend fun getPokemonList(limit: Int, offset: Int) =
        RetrofitInstance.api.getPokemonList(limit, offset)

    suspend fun getPokemonDetail(name: String) =
        RetrofitInstance.api.getPokemonDetail(name)
}
