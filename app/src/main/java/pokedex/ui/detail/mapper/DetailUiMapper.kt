package pokedex.ui.detail.mapper

import pokedex.data.datasource.model.PokemonResultDto
import pokedex.data.datasource.model.entity.PokemonEntity
import pokedex.domain.model.Pokemon

// API → Domain
fun PokemonResultDto.toDomain(): Pokemon {
    return Pokemon(
        name = name,
        url = url
    )
}

// Domain → Entity
fun Pokemon.toEntity(): PokemonEntity {
    return PokemonEntity(
        name = name,
        url = url
    )
}

// Entity → Domain
fun PokemonEntity.toDomain(): Pokemon {
    return Pokemon(
        name = name,
        url = url
    )
}
