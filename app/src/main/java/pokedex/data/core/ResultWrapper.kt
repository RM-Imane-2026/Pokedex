package pokedex.data.core

sealed class ResultWrapper<out T> {

    data class Success<T>(val data: T) : ResultWrapper<T>()

    data class Error(
        val message: String?,
        val code: Int? = null
    ) : ResultWrapper<Nothing>()

    object Loading : ResultWrapper<Nothing>()
}
