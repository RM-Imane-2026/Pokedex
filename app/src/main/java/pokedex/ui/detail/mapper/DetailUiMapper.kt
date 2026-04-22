package pokedex.ui.detail.mapper

import pokedex.domain.model.PokemonDetail
import pokedex.ui.detail.model.PokemonDetailUi

private const val HEIGHT_DIVISOR = 10.0
private const val WEIGHT_DIVISOR = 10.0
private const val HEIGHT_UNIT = " m"
private const val WEIGHT_UNIT = " kg"


fun PokemonDetail.toUi(): PokemonDetailUi {
    val formattedHeight = "${height / HEIGHT_DIVISOR}$HEIGHT_UNIT"
    val formattedWeight = "${weight / WEIGHT_DIVISOR}$WEIGHT_UNIT"

    return PokemonDetailUi(
        id = id,
        name = name,
        height = formattedHeight,
        weight = formattedWeight,
        image = image,
        types = types
    )
}
