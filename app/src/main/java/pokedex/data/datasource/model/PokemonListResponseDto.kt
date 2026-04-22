package pokedex.data.datasource.model

data class PokemonListResponseDto (
    val result: List<PokemonResultDto> = emptyList()
)

data class PokemonResultDto(
    val name: String,
    val url: String
)
