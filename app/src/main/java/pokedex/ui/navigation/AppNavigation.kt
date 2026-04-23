package pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pokedex.ui.detail.screen.PokemonDetailScreen
import pokedex.ui.list.screen.PokemonListScreen

@Composable
fun AppNavigation(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screen.List.route
    ) {

        composable(Screen.List.route) {
            PokemonListScreen(navController)
        }

        composable(Screen.Detail.route) { backStackEntry ->

            val name =
                backStackEntry.arguments?.getString("name").orEmpty()

            PokemonDetailScreen(
                navController = navController,
                name = name
            )
        }
    }
}
