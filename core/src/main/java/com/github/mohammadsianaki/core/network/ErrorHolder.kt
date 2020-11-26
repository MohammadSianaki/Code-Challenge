package com.github.mohammadsianaki.core.network

sealed class ErrorHolder(override val message: String?) : Throwable(message) {

    data class NetworkConnection(
        override val message: String = NetworkExceptionsMessage.NO_INTERNET_CONNECTION
    ) : ErrorHolder(message)

    data class BadRequest(
        override val message: String
    ) : ErrorHolder(message)

    data class UnAuthorized(
        override val message: String? = null
    ) : ErrorHolder(message)

    data class InternalServerError(
        override val message: String
    ) : ErrorHolder(message)

    data class ResourceNotFound(
        override val message: String
    ) : ErrorHolder(message)

    data class Unknown(
        override val message: String
    ) : ErrorHolder(message)

}

enum class ErrorCodes(val code: Int) {
    BAD_REQUEST(400), UNAUTHORIZED(401), NOT_FOUND(404), INTERNAL_SERVER(500)
}
