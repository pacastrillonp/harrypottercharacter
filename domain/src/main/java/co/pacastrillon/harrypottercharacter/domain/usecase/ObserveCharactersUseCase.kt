package co.pacastrillon.harrypottercharacter.domain.usecase

import co.pacastrillon.harrypottercharacter.domain.model.Character
import co.pacastrillon.harrypottercharacter.domain.model.Filter
import co.pacastrillon.harrypottercharacter.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCharactersUseCase @Inject constructor(
    private val repo: CharactersRepository
) {
    operator fun invoke(filter: Filter): Flow<List<Character>> = repo.observeCharacters(filter)
}
