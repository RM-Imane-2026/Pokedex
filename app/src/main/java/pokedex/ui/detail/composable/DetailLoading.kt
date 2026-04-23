package pokedex.ui.detail.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.CircularProgressIndicator
import pokedex.ui.screen.ErrorScreenView

@Composable
fun DetailLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun DetailError(
    onBack: () -> Unit,
    onRetry: () -> Unit
) {
    ErrorScreenView(
        title = "Error de conexión",
        message = "No hemos podido cargar la información.",
        showRetry = true,
        onRetry = onRetry,
        onBack = onBack
    )
}
