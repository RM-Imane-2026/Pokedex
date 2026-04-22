package pokedex.data.datasource.model


data class PokemonDetailDto(
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val types: List<TypeResponseDto>
)

data class Sprites(
    val front_default: String
)

data class TypeResponseDto(
    val type: TypeDto
)

data class TypeDto(
    val name: String
)
