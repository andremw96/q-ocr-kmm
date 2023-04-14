package com.andremw96.qocrkmm.model

data class CompletionSchema(
    val choices: List<Choice>,
    val created: Int,
    val id: String,
    val model: String,
    val objectX: String,
    val completionTokens: Int,
    val promptTokens: Int,
    val totalTokens: Int
) {
    data class Choice(
        val finishReason: String,
        val index: Int,
        val logprobs: Int,
        val text: String
    )

    data class Usage(
        val completionTokens: Int,
        val promptTokens: Int,
        val totalTokens: Int
    )
}
