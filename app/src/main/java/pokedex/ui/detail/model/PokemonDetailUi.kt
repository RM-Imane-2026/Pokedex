package pokedex.ui.detail.model

data class PokemonDetailUi(
    val id: Int,
    val name: String,
    val height: String,
    val weight: String,
    val image: String?,
    val types: List<String>
)
