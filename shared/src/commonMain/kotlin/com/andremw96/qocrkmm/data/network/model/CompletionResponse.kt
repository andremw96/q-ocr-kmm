package com.andremw96.qocrkmm.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompletionResponse(
    @SerialName("choices")
    val choices: List<Choice>,
    @SerialName("created")
    val created: Int,
    @SerialName("id")
    val id: String,
    @SerialName("model")
    val model: String,
    @SerialName("object")
    val objectX: String,
    @SerialName("usage")
    val usage: Usage
) {
    @Serializable
    data class Choice(
        @SerialName("finish_reason")
        val finishReason: String,
        @SerialName("index")
        val index: Int,
        @SerialName("logprobs")
        val logprobs: Int,
        @SerialName("text")
        val text: String
    )

    @Serializable
    data class Usage(
        @SerialName("completion_tokens")
        val completionTokens: Int,
        @SerialName("prompt_tokens")
        val promptTokens: Int,
        @SerialName("total_tokens")
        val totalTokens: Int
    )
}
