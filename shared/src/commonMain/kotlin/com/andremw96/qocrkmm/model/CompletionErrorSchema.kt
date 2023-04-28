package com.andremw96.qocrkmm.model

import kotlinx.serialization.Serializable

@Serializable
data class CompletionErrorSchema(
    val code: String?,
    val message: String,
    val `param`: String?,
    val type: String
)
