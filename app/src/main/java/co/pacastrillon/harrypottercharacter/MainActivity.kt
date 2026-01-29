package co.pacastrillon.harrypottercharacter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import co.pacastrillon.harrypottercharacter.character.CharactersScreen
import co.pacastrillon.harrypottercharacter.ui.theme.HarryPotterCharacterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HarryPotterCharacterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CharactersScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}