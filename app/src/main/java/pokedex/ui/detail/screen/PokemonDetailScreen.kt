package pokedex.ui.detail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import pokedex.ui.detail.model.PokemonDetail
import androidx.hilt.navigation.compose.hiltViewModel
import pokedex.ui.detail.viewmodel.PokemonDetailViewModel
import pokedex.ui.core.state.UiState
import pokedex.ui.screen.ErrorScreenView


@Composable
fun PokemonDetailScreen(
    navController: NavController,
    name: String,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(name) {
        viewModel.loadPokemonDetail(name)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        containerColor = Color(0xFFF4F6FB)
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = Color(0xFFF4F6FB)
        ) {
            when (state) {
                is UiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is UiState.Error -> {
                    ErrorScreenView(
                        title = "Error de conexión",
                        message = "No hemos podido cargar la información de este Pokémon.\n" +
                                "Comprueba tu conexión a Internet e inténtalo de nuevo.",
                        showRetry = false,
                        onBack = { navController.popBackStack() }
                    )
                }
                is UiState.Success -> {
                    val pokemon = (state as UiState.Success<PokemonDetail>).data

                    // Contenido principal con scroll
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        // Fila superior: botón de back SOLO icono, alineado a la izquierda
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Volver",
                                    tint = Color(0xFF1E2A3B)
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "Detalles de ${pokemon.name}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1E2A3B)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AsyncImage(
                                    model = pokemon.sprites.front_default,
                                    contentDescription = pokemon.name,
                                    modifier = Modifier
                                        .size(160.dp)
                                        .clip(RoundedCornerShape(16.dp))
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "ID: ${pokemon.id}",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF1E2A3B)
                                )
                                Text(
                                    text = "Nombre: ${pokemon.name}",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF1E2A3B)
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Altura: ${pokemon.height}",
                                    fontSize = 16.sp,
                                    color = Color(0xFF4A5A73)
                                )
                                Text(
                                    text = "Peso: ${pokemon.weight}",
                                    fontSize = 16.sp,
                                    color = Color(0xFF4A5A73)
                                )

                                Spacer(modifier = Modifier.height(16.dp))
                                HorizontalDivider()
                                Spacer(modifier = Modifier.height(12.dp))

                                Text(
                                    text = "Tipos:",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF1E2A3B)
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Column(
                                    verticalArrangement = Arrangement.spacedBy(6.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    pokemon.types.forEach { typeSlot ->
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .clip(CircleShape)
                                                    .background(Color(0xFF64B5F6))
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                text = "- ${typeSlot.type.name}",
                                                fontSize = 15.sp,
                                                color = Color(0xFF374457)
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        // Espacio para que el botón inferior no quede pegado
                        Spacer(modifier = Modifier.height(24.dp))

                        // Botón de volver bonito en la parte de abajo
                        FilledTonalButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Volver al listado")
                        }
                    }
                }

                else -> {}
            }
        }
    }
}
