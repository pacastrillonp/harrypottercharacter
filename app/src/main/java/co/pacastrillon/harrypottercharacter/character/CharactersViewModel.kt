package co.pacastrillon.harrypottercharacter.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.pacastrillon.harrypottercharacter.domain.repository.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repo: CharactersRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CharactersUiState>(CharactersUiState.Idle)
    val uiState: StateFlow<CharactersUiState> = _uiState.asStateFlow()

    fun getCharacters() {
        viewModelScope.launch {
            _uiState.value = CharactersUiState.Loading
            runCatching { repo.getCharacters() }
                .onSuccess { list ->
                    _uiState.value = CharactersUiState.Success(list)
                }
                .onFailure { e ->
                    _uiState.value = CharactersUiState.Error(e.message ?: "Unknown error")
                }
        }
    }
}