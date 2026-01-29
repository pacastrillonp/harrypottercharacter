package co.pacastrillon.harrypottercharacter.domain.repository
import co.pacastrillon.harrypottercharacter.domain.model.Character

interface CharactersRepository {
    suspend fun getCharacters(): List<Character>
}