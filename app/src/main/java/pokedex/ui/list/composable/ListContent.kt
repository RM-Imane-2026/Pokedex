package pokedex.ui.list.composable

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pokedex.ui.list.model.PokemonItemUi

@Composable
fun ListContent(
    list: List<PokemonItemUi>,
    onItemClick: (String) -> Unit,
    onNextPage: () -> Unit,
    onPreviousPage: () -> Unit,
    hasPreviousPage: Boolean,
    modifier: Modifier = Modifier
) {

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {

        ListHeader()

        Spacer(modifier = Modifier.height(8.dp))

        PokemonListContent(
            list = list,
            onItemClick = onItemClick,
            onNextPage = onNextPage,
            onPreviousPage = onPreviousPage,
            hasPreviousPage = hasPreviousPage
        )
    }
}
