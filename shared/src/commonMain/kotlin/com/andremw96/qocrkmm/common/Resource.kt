package com.andremw96.qocrkmm.common

sealed class Resource<T, R> {
    data class Success<T, R>(val data: T) : Resource<T, R>()
    data class Error<T, R>(val error: R) : Resource<T, R>()
}
