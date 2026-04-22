package pokedex.ui.core.model

data class PokemonListResponse(
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
){
    val id: Int
        get() = url
            .trimEnd('/')
            .split("/")
            .last()
            .toInt()
    val formattedName: String
        get() = name.replaceFirstChar { it.uppercase() }
}
