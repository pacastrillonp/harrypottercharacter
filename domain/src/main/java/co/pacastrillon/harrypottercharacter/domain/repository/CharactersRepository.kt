package co.pacastrillon.harrypottercharacter.domain.repository

import co.pacastrillon.harrypottercharacter.domain.model.Character
import co.pacastrillon.harrypottercharacter.domain.model.Filter
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun observeCharacters(filter: Filter): Flow<List<Character>>
    suspend fun refreshCharacters(filter: Filter)
    fun observeCharacter(id: String): Flow<Character?>
}