package co.pacastrillon.harrypottercharacter.domain.usecase

import co.pacastrillon.harrypottercharacter.domain.model.Filter
import co.pacastrillon.harrypottercharacter.domain.repository.CharactersRepository
import javax.inject.Inject

class RefreshCharactersUseCase @Inject constructor(
    private val repo: CharactersRepository
) {
    suspend operator fun invoke(filter: Filter) = repo.refreshCharacters(filter)
}