package co.pacastrillon.harrypottercharacter.domain.model

data class Character(
    val id: String,
    val name: String,
    val house: String?,
    val image: String?
)