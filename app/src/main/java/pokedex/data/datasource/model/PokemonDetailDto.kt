package pokedex.data.datasource.model

import com.squareup.moshi.Json

data class PokemonDetailDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "height") val height: Int,
    @Json(name = "weight") val weight: Int,
    @Json(name = "sprites") val sprites: SpritesDto,
    @Json(name = "types") val types: List<TypeSlotDto>
)

data class SpritesDto(
    @Json(name = "other") val other: OtherDto
)

data class OtherDto(
    @Json(name = "official-artwork") val officialArtwork: OfficialArtworkDto
)

data class OfficialArtworkDto(
    @Json(name = "front_default") val frontDefault: String?
)

data class TypeSlotDto(
    @Json(name = "type") val type: TypeDto
)

data class TypeDto(
    @Json(name = "name") val name: String
)
