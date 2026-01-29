package co.pacastrillon.harrypottercharacter.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    @SerialName("id") val id: String? = null,
    @SerialName("name") val name: String = "",
    @SerialName("house") val house: String? = null,
    @SerialName("image") val image: String? = null,
    @SerialName("actor") val actor: String? = null,
    @SerialName("alive") val alive: Boolean? = null,
    @SerialName("hogwartsStudent") val hogwartsStudent: Boolean? = null,
    @SerialName("hogwartsStaff") val hogwartsStaff: Boolean? = null
)
