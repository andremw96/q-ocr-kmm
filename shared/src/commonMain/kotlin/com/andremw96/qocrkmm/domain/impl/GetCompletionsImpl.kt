package com.andremw96.qocrkmm.domain.impl

import com.andremw96.qocrkmm.common.Resource
import com.andremw96.qocrkmm.data.network.model.CompletionRequest
import com.andremw96.qocrkmm.domain.GetCompletions
import com.andremw96.qocrkmm.model.CompletionErrorSchema
import com.andremw96.qocrkmm.model.CompletionSchema
import com.andremw96.qocrkmm.repository.ChatGptRepository

class GetCompletionsImpl(
    private val chatGptRepository: ChatGptRepository
) : GetCompletions {
    override suspend fun invoke(request: CompletionRequest): Resource<CompletionSchema, CompletionErrorSchema> {
        return chatGptRepository.completions(request)
    }
}
