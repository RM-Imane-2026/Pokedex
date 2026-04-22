package pokedex.ui.core.navigation

sealed class Screen(val route: String) {

    object List : Screen("list")

    object Detail : Screen("detail/{name}") {

        fun createRoute(name: String): String {
            return "detail/$name"
        }
    }
}
