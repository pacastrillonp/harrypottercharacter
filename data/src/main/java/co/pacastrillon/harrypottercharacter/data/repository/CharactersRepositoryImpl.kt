package co.pacastrillon.harrypottercharacter.data.repository

import co.pacastrillon.harrypottercharacter.data.mapper.toCharacter
import co.pacastrillon.harrypottercharacter.data.remote.HpApiService
import co.pacastrillon.harrypottercharacter.domain.model.Character
import co.pacastrillon.harrypottercharacter.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val api: HpApiService
) : CharactersRepository {

    override suspend fun getCharacters(): List<Character> =
        api.getCharacters().map { it.toCharacter() }

}