package phokedex

data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val types: List<TypeSlot>
)

data class Sprites(
    val front_default: String?
)

data class TypeSlot(
    val type: Type
)

data class Type(
    val name: String
)
