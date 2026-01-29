package co.pacastrillon.harrypottercharacter.character

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    vm: CharactersViewModel = hiltViewModel()
) {
    val state by vm.uiState.collectAsState()

    LaunchedEffect(Unit) {
        vm.getCharacters()
    }

    Box(modifier = modifier.fillMaxSize()) {
        when (val s = state) {
            CharactersUiState.Idle -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text("Idle")
                }
            }

            CharactersUiState.Loading -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is CharactersUiState.Error -> {
                Column(
                    Modifier.fillMaxSize().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Error: ${s.message}")
                    Button(onClick = { vm.getCharacters() }) {
                        Text("Reintentar")
                    }
                }
            }

            is CharactersUiState.Success -> {
                Column(Modifier.fillMaxSize().padding(16.dp)) {
                    Text(
                        "Total: ${s.characters.size}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.height(12.dp))

                    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(s.characters) { c ->
                            Card(Modifier.fillMaxWidth()) {
                                Column(Modifier.padding(12.dp)) {
                                    Text(c.name, style = MaterialTheme.typography.titleSmall)
                                    Text("House: ${c.house ?: "-"}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

