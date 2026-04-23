package pokedex.data.datasource.mapper

import pokedex.data.datasource.model.PokemonDetailDto
import pokedex.data.datasource.model.PokemonDto
import pokedex.data.datasource.model.PokemonListResponseDto
import pokedex.domain.model.Pokemon
import pokedex.domain.model.PokemonDetail
import pokedex.domain.model.PokemonListResponse

private const val SPRITE_BASE_URL =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
private const val SPRITE_EXTENSION = ".png"

fun PokemonListResponseDto.toDomain(): PokemonListResponse {
    return PokemonListResponse(
        count = count,
        next = next,
        previous = previous,
        results = result.map { it.toDomain() }
    )
}

fun PokemonDto.toDomain(): Pokemon {
    val id = url
        .trimEnd('/')
        .split("/")
        .last()
        .toInt()

    return Pokemon(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        url = "$SPRITE_BASE_URL$id$SPRITE_EXTENSION"
    )
}

fun PokemonDetailDto.toDomain(): PokemonDetail {
    return PokemonDetail(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        height = height,
        weight = weight,
        image = "$SPRITE_BASE_URL$id$SPRITE_EXTENSION",
        types = types.map { it.type.name.replaceFirstChar { it.uppercase() } }
    )
}
