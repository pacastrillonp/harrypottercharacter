package co.pacastrillon.harrypottercharacter.data.mapper

import co.pacastrillon.harrypottercharacter.data.remote.dto.CharacterDto
import co.pacastrillon.harrypottercharacter.domain.model.Character

fun CharacterDto.toCharacter(): Character = Character(
    id = id,
    name = name,
    house = house,
    image = image
)