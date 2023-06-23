package com.andremw96.qocrkmm.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompletionRequest(
    @SerialName("logprobs")
    val logprobs: Int? = null,
    @SerialName("max_tokens")
    val maxTokens: Int = 512,
    @SerialName("model")
    val model: String = "text-davinci-003",
    @SerialName("n")
    val n: Int = 0,
    @SerialName("prompt")
    val prompt: String,
    @SerialName("stop")
    val stop: String = "",
    @SerialName("stream")
    val stream: Boolean = false,
    @SerialName("temperature")
    val temperature: Double = 0.2,
    @SerialName("top_p")
    val topP: Int = 1,
)
