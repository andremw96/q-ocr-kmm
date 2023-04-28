package com.andremw96.qocrkmm.data.network.model

sealed class ApiResponse<T, R> {
    data class Success<T, R>(val data: T) : ApiResponse<T, R>()
    data class Error<T, R>(val error: R) : ApiResponse<T, R>()
}
