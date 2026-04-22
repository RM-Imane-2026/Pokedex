package phokedex

data class PokemonListResponse(
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
){

    // ID calculado automáticamente desde la URL
    val id: Int
        get() = url
            .trimEnd('/')
            .split("/")
            .last()
            .toInt()

    // Nombre formateado
    val formattedName: String
        get() = name.replaceFirstChar { it.uppercase() }
}
