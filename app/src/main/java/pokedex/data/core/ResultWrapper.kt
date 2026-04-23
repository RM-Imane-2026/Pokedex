package pokedex.data.core

sealed class ResultWrapper<out T> {
    data class Success<T>(val data: T) : ResultWrapper<T>()
    data class Error(val error: UiError) : ResultWrapper<Nothing>()
    object Loading : ResultWrapper<Nothing>()
}

sealed class UiError {
    object Network : UiError()
    object NotFound : UiError()
    data class Unknown(val message: String?) : UiError()
}

