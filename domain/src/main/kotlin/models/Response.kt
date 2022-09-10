package models

data class Response<T> (
    val response: T?,
    val message: String?,
    val exception: Exception?
)