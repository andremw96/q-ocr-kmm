package com.andremw96.qocrkmm.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompletionRequest(
    @SerialName("logprobs")
    val logprobs: Int,
    @SerialName("max_tokens")
    val maxTokens: Int,
    @SerialName("model")
    val model: String,
    @SerialName("n")
    val n: Int,
    @SerialName("prompt")
    val prompt: String,
    @SerialName("stop")
    val stop: String,
    @SerialName("stream")
    val stream: Boolean,
    @SerialName("temperature")
    val temperature: Int,
    @SerialName("top_p")
    val topP: Int
)
