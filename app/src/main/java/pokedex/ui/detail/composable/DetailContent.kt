package pokedex.ui.detail.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pokedex.ui.detail.model.PokemonDetailUi

@Composable
fun DetailContent(
    pokemon: PokemonDetailUi, onBack: () -> Unit, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        DetailHeader(
            name = pokemon.name, onBack = onBack
        )

        Spacer(modifier = Modifier.height(16.dp))

        DetailCard(pokemon = pokemon)

        Spacer(modifier = Modifier.height(24.dp))

        BackButtonSection(onBack = onBack)
    }
}
