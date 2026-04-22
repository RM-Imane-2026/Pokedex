package pokedex.domain.model

data class PokemonDetail(
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val types: List<TypeResponse>
)

data class Sprites(
    val front_default: String
)

data class TypeResponse(
    val type: Type
)

data class Type(
    val name: String
)
