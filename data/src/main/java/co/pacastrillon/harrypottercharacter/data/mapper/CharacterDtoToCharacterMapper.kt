package co.pacastrillon.harrypottercharacter.data.mapper

import co.pacastrillon.harrypottercharacter.data.remote.dto.CharacterDto
import co.pacastrillon.harrypottercharacter.domain.model.Character
import java.util.Locale

fun CharacterDto.toCharacter(): Character {
    val stableId = id?.takeIf { it.isNotBlank() } ?: run {
        val base = (name.trim() + "|" + (actor ?: "").trim())
            .lowercase(Locale.ROOT)
        base.hashCode().toString()
    }

    return Character(
        id = stableId,
        name = name,
        house = house,
        image = image
    )
}