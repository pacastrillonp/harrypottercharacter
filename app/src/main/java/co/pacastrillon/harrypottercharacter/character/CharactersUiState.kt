package co.pacastrillon.harrypottercharacter.character
import co.pacastrillon.harrypottercharacter.domain.model.Character

sealed interface CharactersUiState {
    data object Idle : CharactersUiState
    data object Loading : CharactersUiState
    data class Success(val characters: List<Character>) : CharactersUiState
    data class Error(val message: String) : CharactersUiState
}