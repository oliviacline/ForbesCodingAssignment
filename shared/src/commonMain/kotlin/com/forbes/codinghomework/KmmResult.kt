package com.forbes.codinghomework

// shamelessly stolen from:
// https://www.raywenderlich.com/22030171-reactive-streams-on-kotlin-sharedflow-and-stateflow

sealed class KmmResult<out T> {
    data class Success<T>(val value: T) : KmmResult<T>()
    data class Failure(val cause: Throwable) : KmmResult<Nothing>()
}

fun <T> KmmResult(block: () -> T): KmmResult<T> = try {
    KmmResult.Success(block())
} catch (t: Throwable) {
    KmmResult.Failure(t)
}

fun <T> KmmResult<T>.requireValue(): T = when (this) {
    is KmmResult.Success -> value
    is KmmResult.Failure -> throw cause
}