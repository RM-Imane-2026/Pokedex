package pokedex.domain.model

data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val image: String?,
    val types: List<String>
)
