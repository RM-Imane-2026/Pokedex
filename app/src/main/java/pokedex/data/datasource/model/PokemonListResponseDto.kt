package pokedex.data.datasource.model

import com.squareup.moshi.Json

data class PokemonListResponseDto (
    val count: Int,
    val next: String?,
    val previous: String?,
    @Json(name = "results")
    val result: List<PokemonDto> = emptyList()
)

data class PokemonDto(
    val name: String,
    val url: String
)
