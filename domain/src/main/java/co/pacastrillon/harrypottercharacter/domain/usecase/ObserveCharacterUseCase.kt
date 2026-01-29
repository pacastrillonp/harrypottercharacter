package co.pacastrillon.harrypottercharacter.domain.usecase

import co.pacastrillon.harrypottercharacter.domain.model.Character
import co.pacastrillon.harrypottercharacter.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCharacterUseCase @Inject constructor(
    private val repo: CharactersRepository
) {
    operator fun invoke(id: String): Flow<Character?> = repo.observeCharacter(id)
}