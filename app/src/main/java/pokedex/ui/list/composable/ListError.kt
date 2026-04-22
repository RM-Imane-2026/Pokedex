package pokedex.ui.list.composable

import androidx.compose.runtime.Composable
import pokedex.ui.core.screen.ErrorScreenView

@Composable
fun ListError(
    onRetry: () -> Unit,
    onBack: () -> Unit
) {
    ErrorScreenView (
        title = "Error de conexión",
        message = "No hemos podido cargar el listado.",
        showRetry = true,
        onRetry = onRetry,
        onBack = onBack
    )
}