package pokedex.ui.list.mapper

import pokedex.domain.model.Pokemon
import pokedex.ui.list.model.PokemonItemUi

fun Pokemon.toUi(): PokemonItemUi {
    return PokemonItemUi(
        id = id,
        name = name,
        image = imagenUrl
    )
}
