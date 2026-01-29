package co.pacastrillon.harrypottercharacter.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.pacastrillon.harrypottercharacter.domain.model.Filter
import co.pacastrillon.harrypottercharacter.domain.usecase.ObserveCharactersUseCase
import co.pacastrillon.harrypottercharacter.domain.usecase.RefreshCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val observeCharacters: ObserveCharactersUseCase,
    private val refreshCharacters: RefreshCharactersUseCase
) : ViewModel() {

    private val filter = MutableStateFlow<Filter>(Filter.All)

    private val _uiState = MutableStateFlow<CharactersUiState>(CharactersUiState.Idle)
    val uiState: StateFlow<CharactersUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            filter.flatMapLatest { observeCharacters(it) }
                .collect { list ->
                    _uiState.value = CharactersUiState.Success(list)
                }
        }
        refresh()
    }

    fun setFilter(newFilter: Filter) {
        filter.value = newFilter
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.value = CharactersUiState.Loading
            runCatching { refreshCharacters(filter.value) }
                .onFailure { e ->
                    _uiState.value = CharactersUiState.Error(e.message ?: "Unknown error")
                }
        }
    }

    fun getCharacters() = refresh()
}
