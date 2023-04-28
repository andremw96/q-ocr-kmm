package com.andremw96.qocrkmm.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompletionErrorResponse(
    @SerialName("error")
    val error: Error
) {
    @Serializable
    data class Error(
        @SerialName("code")
        val code: String?,
        @SerialName("message")
        val message: String,
        @SerialName("param")
        val `param`: String?,
        @SerialName("type")
        val type: String
    )
}
