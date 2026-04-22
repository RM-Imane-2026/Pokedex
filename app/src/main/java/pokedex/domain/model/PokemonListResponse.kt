package pokedex.domain.model

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)
data class Pokemon(
    val id: Int,
    val name: String,
    val url: String
)